-- src/todo_server/sql/tasks.sql

-- :name all-tasks :? :*
-- :doc pull all entries
select * from todo;

-- :name add-task :! 
-- :doc add todo item
insert into todo (task)
values (:task)

-- :name delete-task :!
-- :doc remove single task
delete from todo where id = :id

-- :name edit-task :!
-- :doc edit mistakes on tasks
update todo set task = :task where id = :id

-- :name delete-all :!
-- :doc delete all tasks
delete from todo

-- :name update-status :!
-- :doc completed is true or false
update todo set completed = :completed where id = :id