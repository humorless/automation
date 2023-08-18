(ns automation.auto
  (:require [lambdaisland.embedkit :as e]))

(def config
  (read-string (slurp "dev/config.edn")))

(def conn
  (e/connect config))

(defn run [opts]
  (e/trigger-db-fn! conn (:db-name config) :sync_schema)
  (e/trigger-db-fn! conn (:db-name config) :rescan_values))
