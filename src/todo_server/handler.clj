(ns todo-server.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [todo_server.sql.tasks :as tasks]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.middleware.anti-forgery :refer [wrap-anti-forgery]]
            [ring.middleware.session :refer [wrap-session]]))

(def mysql-db {:subprotocol "mysql"
               :subname "//127.0.0.1:3306/todo_app"
               :user "root"
               :password "Growth2021!"})

;;need an update function (axios.put)
;;need a delete all function 
;;need a unique table per user?


(defroutes app-routes
  (GET "/api/get" [] (tasks/all-tasks mysql-db))
  (POST "/api/insert" {body :body}
    (let [task (get body :todoItem)]
      (tasks/add-task mysql-db {:task task})))
  (DELETE "/api/deleteall" []
    (tasks/delete-all mysql-db)
    {})
  (DELETE "/api/delete" {body :body}
    (let [id (get body :id)]
      (tasks/delete-task mysql-db {:id id})))
  (PUT "/api/update" {body :body}
    (if (contains? body :completed)
      (let [completed (get body :completed) id (get body :id)]
        (tasks/update-status mysql-db {:completed completed :id id}))
      (let [task (get body :task) id (get body :id)]
        (tasks/edit-task mysql-db {:id id :task task}))))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-json-body {:keywords? true})
      wrap-json-response
      (wrap-defaults api-defaults)
      (wrap-cors :access-control-allow-origin [#"http://localhost:3000"] :access-control-allow-methods [:get :put :post :delete])))


