package com.jih10157.Jihsk.Effect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import com.jih10157.Jihsk.Main;
import com.jih10157.Jihsk.util.Plugin.ConfigLoader;
import com.jih10157.Jihsk.util.Mail.SSLEmailSender;
import com.jih10157.Jihsk.util.Mail.TLSEmailSender;
import ch.njol.skript.lang.Effect;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffSendEmail extends Effect {

    static {
        Skript.registerEffect(EffSendEmail.class, "[jih[sk].[ ]]send email message %string% subject %string% to %string%");
        Main.Effamount++;
    }
    private Expression<String> address;
    private Expression<String> message;
    private Expression<String> subject;

    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        if(Main.mail) {
            Skript.error("Mail 라이브러리가 로드되어있지 않아 메일기능을 사용하실수 없습니다.");
            return false;
        }
        this.message = (Expression<String>)e[0];
        this.subject = (Expression<String>)e[1];
        this.address = (Expression<String>)e[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) { return "Jihsk EffSendEmail"; }

    @Override
    protected void execute(Event e) {
        if (message.getSingle(e) != null && address.getSingle(e) != null && subject.getSingle(e) != null) {
            if (ConfigLoader.emailTLSRequired) {
                new TLSEmailSender().send(subject.getSingle(e), message.getSingle(e), address.getSingle(e));
            } else {
                new SSLEmailSender().send(subject.getSingle(e), message.getSingle(e), address.getSingle(e));
            }
        }
    }
}
