(defproject cljminecraft "1.0.6-SNAPSHOT"
  :description "Clojure for Bukkit Minecraft"
  :dependencies [[org.clojure/clojure         "1.5.1"]
                 [org.clojure/tools.logging   "0.2.6"]
                 [org.clojure/tools.nrepl     "0.2.3"]
                 [clojure-complete            "0.2.3"]
                 [cheshire                    "5.2.0"]
                 [org.reflections/reflections "0.9.8"]]
                 [org.bukkit/bukkit           "1.8.8-R0.1-SNAPSHOT"]
                 [org.spigotmc/spigot-api     "1.8.8-R0.1-SNAPSHOT"]
  :profiles {:dev {:dependencies []}}
  :javac-options [ "-d" "classes/" "-source" "1.6" "-target" "1.6"]
  :java-source-paths ["javasrc"]
  :uberjar-exclusions [#"(org|com|gnu)[/](bukkit|avaje|yaml|getspout|json|trove|spigotmc)[/](.*)"
                       #"com[/]google[/]common[/](.*)"
                       #"org[/]apache[/]commons[/](.*)"
                       #"javax[/]persistence[/](.*)"
                       #"net[/]sf[/]cglib[/](.*)"]
  :repositories [["spigot.snapshots" "https://hub.spigotmc.org/nexus/content/repositories/snapshots/"]]
  )
