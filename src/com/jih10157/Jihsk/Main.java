package com.jih10157.Jihsk;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.jih10157.Jihsk.CallEvent.SmartMovingEventListener;
import com.jih10157.Jihsk.CallEvent.VotfilerVoteEventListener;
import com.jih10157.Jihsk.VariableTriggers.varData.VTVar;
import com.jih10157.Jihsk.VariableTriggers.varData.VTVar_1;
import com.jih10157.Jihsk.VariableTriggers.varData.VTVar_2;
import com.jih10157.Jihsk.util.Plugin.ConfigLoader;
import com.jih10157.Jihsk.util.Plugin.Downloader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Main extends JavaPlugin implements Listener {
    public static PluginManager pm;
    public static VTVar vtVar;
	private static Main instance;
    private static ConfigLoader loader;
    public static boolean mail = false;
    public static int Effamount = 0;
    public static int Evtamount = 0;
    public static int Expramount = 0;
    private String newver;
    private String ver;
    @Override
    public void onLoad() {
        instance = this;
        pm = Bukkit.getPluginManager();
        try {
            javax.mail.Message.class.getName();
            getLogger().info("Mail 기능을 확인하였습니다.");
            mail = true;
            return;
        } catch (NoClassDefFoundError ignored) {}
        File lib = new File(getDataFolder().getAbsolutePath()+File.separator+"lib"+File.separator+"mail-1.4.7.jar");
        if (!lib.getParentFile().exists()) {
            lib.getParentFile().mkdirs();
        }
        if(!lib.exists()) {
            Downloader downloader = new Downloader(lib.getParentFile().getAbsolutePath(), "http://jihskver.dothome.co.kr/mail-1.4.7.jar", lib.getName());
            if(!downloader.download()) {
                getLogger().info("Mail Lib 다운에러: " + lib.getName());
                getLogger().info("메일 기능을 사용할수 없습니다.");
                mail = false;
                return;
            }
        }
        if(!lib.exists()) {
            getLogger().info("Mail Lib 에러: " + lib.getName());
            getLogger().info("메일 기능을 사용할수 없습니다.");
            mail = false;
            return;
        }
        addClassPath(getJarUrl(lib));
        getLogger().info("메일 라이브러리가 정상적으로 로드되었습니다.");
        mail = true;
    }
    @Override
    public void onEnable() {
        loader = new ConfigLoader(this);
        loader.Load();
        pm.registerEvents(this, this);
		if (pm.getPlugin("Skript") != null) {
            getLogger().info("스크립트 인식");
            try {
                getServer().getMessenger().registerIncomingPluginChannel(this, "SmartMoving 2.3", new SmartMovingEventListener());
                getServer().getMessenger().registerIncomingPluginChannel(this, "SmartMoving 2.4", new SmartMovingEventListener());
                getServer().getMessenger().registerIncomingPluginChannel(this, "SmartMoving 2.3.1", new SmartMovingEventListener());
            } catch(Exception ignored) { }
            SkriptAddon sk = Skript.registerAddon(this);
            try {
                sk.loadClasses("com.jih10157.Jihsk", "Effect", "Expression", "Event");
                if (pm.getPlugin("Votifier") != null) {
                    getLogger().info("Votifier 인식");
                    pm.registerEvents(new VotfilerVoteEventListener(), this);
                    sk.loadClasses("com.jih10157.Jihsk", "Votifier");
                }
                if (pm.getPlugin("VariableTriggers") != null) {
                    Plugin pl = pm.getPlugin("VariableTriggers");
                    VTVar_1 vtVar_1 = new VTVar_1(pl);
                    if(vtVar_1.check()) {
                        vtVar = vtVar_1;
                        getLogger().info("VariableTriggers 인식");
                        sk.loadClasses("com.jih10157.Jihsk", "VariableTriggers");
                    } else {
                        VTVar_2 vtVar_2 = new VTVar_2(pl);
                        if(vtVar_2.check()) {
                            vtVar = vtVar_2;
                            getLogger().info("VariableTriggers 2 인식");
                            sk.loadClasses("com.jih10157.Jihsk", "VariableTriggers");
                        }
                    }
                }
                if (pm.getPlugin("WorldEdit") != null) {
                    getLogger().info("WorldEdit 인식");
                    sk.loadClasses("com.jih10157.Jihsk", "WorldEdit");
                }
                if (pm.getPlugin("ServerWebStatus") != null) {
                    getLogger().info("ServerWebStatus 인식");
                    sk.loadClasses("com.jih10157.Jihsk", "ServerWebStatus");
                }
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
            getLogger().info("Effect: "+Effamount+"개, Event: "+Evtamount+"개, Expression: "+Expramount+"개");
            getLogger().info("플러그인 활성화 완료!");
            getLogger().info("제작자: 짛(Uni_Jih)");
            getLogger().info("디스코드: Uni_Jih#7500");
            this.newver = updateCheck();
            this.ver = getDescription().getVersion();
            assert this.newver != null;
            if (!(this.newver.equals(this.ver))) {
                getLogger().info("최신버전이 있습니다! 현재버전: "+this.ver+" 최신버전: "+this.newver);
            }
        }
	}
    @Override
	public void onDisable() {
        loader.Load();
	    if (!(this.newver.equals(this.ver))) {
            if(ConfigLoader.autoUpdate) {
                Downloader downloader = new Downloader(getFile().getParentFile().getAbsolutePath(),"http://jihskver.dothome.co.kr/Jihsk.jar", "Jihsk.jar");
                if(downloader.download()) {
                    getLogger().info("플러그인이 자동으로 업데이트되었습니다.");
                } else {
                    getLogger().info("플러그인을 업데이트를 진행하던 도중 오류가 발생하였습니다.");
                }
            }
        }
        getLogger().info("플러그인 비활성화 완료!");
        getLogger().info("제작자: 짛(Uni_Jih)");
        getLogger().info("디스코드: Uni_Jih#7500");
	}
    private static String updateCheck() {
        try {
            URL url = new URL("http://jihskver.dothome.co.kr/");
            BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()));
            String version = r.readLine();
            r.close();
            return version;
        } catch (Exception e) {
            Bukkit.getLogger().info("새로운 버전을 불러오는데 실패하였습니다. " + e.getMessage());
        }
        return null;
    }
	@EventHandler
	public void check(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.getName().contains("Uni_Jih")) {
            p.sendMessage("§6[Jihsk] §6v" + getDescription().getVersion());
        }
        if (p.isOp()) {
            if (!(this.newver.equals(this.ver))) {
                p.sendMessage("§6[Jihsk] §f최신버전이 있습니다! 현재버전: " + this.ver + " 최신버전: " + this.newver);
                p.sendMessage("§6[Jihsk] §f이 메시지는 오피에게만 출력됩니다.");
            }
        }
    }

    private static void addClassPath(URL url) {
        URLClassLoader sysloader = (URLClassLoader)ClassLoader.getSystemClassLoader();
        Class<URLClassLoader> sysclass = URLClassLoader.class;
        try {
            Method method = sysclass.getDeclaredMethod("addURL", URL.class);
            method.setAccessible(true);
            method.invoke(sysloader, url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static URL getJarUrl(File file) {
        try {
            return new URL("jar:" + file.toURI().toURL().toExternalForm() + "!/");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Main getInstance() { return instance; }
}