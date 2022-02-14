(ns todo_server.sql.tasks
  (:require [hugsql.core :as hugsql]))

(hugsql/def-db-fns "todo_server/sql/tasks.sql")