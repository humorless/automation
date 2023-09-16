(ns automation.auto
  (:require
   [lambdaisland.embedkit :as e]
   [lambdaisland.embedkit.repl :as r]))

(def config
  (read-string (slurp "dev/config.edn")))

(def conn
  (e/connect config))

(defn run [opts]
  (e/trigger-db-fn! conn (:db-name config) :sync_schema)
  (e/trigger-db-fn! conn (:db-name config) :rescan_values))

;; populate cache
(e/populate-cache conn)

(defn create-card [opts]
  (let [db (e/find-database conn "Sample Database")
        card (e/native-card {:name "order card"
                             :variables {:start_date {:editable? true
                                                      :type "date"}
                                         :end_date {:editable? true
                                                    :type "date"}}
                             :database (:id db)
                             :sql "SELECT * FROM orders [[WHERE created_at BETWEEN {{start_date}} AND {{end_date}}]]"})]
    (r/browse! (e/find-or-create! conn card))))

(defn create-dashboard [opts]
  (let [db (e/find-database conn "Sample Database")
        card1 (e/native-card {:name "order card"
                              :database (:id db)
                              :sql "SELECT * FROM orders"})
        card2 (e/native-card {:name "invoice card"
                              :database (:id db)
                              :sql "SELECT * FROM invoices"})
        dashboard (e/find-or-create! conn (e/dashboard {:name "Sample DB dashboard"
                                                        :cards [{:card card1
                                                                 :x 0 :y 0
                                                                 :width 5 :height 5}
                                                                {:card card2
                                                                 :x 5 :y 0
                                                                 :width 5 :height 5}]}))]
    (r/browse! dashboard)))
