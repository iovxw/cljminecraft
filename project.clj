(defproject cljminecraft "1.0.0"
  :description "Clojure for Bukkit Minecraft"
  :repositories [["spigot.snapshots" "https://hub.spigotmc.org/nexus/content/repositories/snapshots/"]]
  :profiles {:provided
             {:dependencies
              [[org.bukkit/bukkit "1.9-R0.1-SNAPSHOT"]]}}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :java-source-paths ["javasrc"])
