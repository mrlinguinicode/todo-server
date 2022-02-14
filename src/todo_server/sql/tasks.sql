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
delete from todo where task = :task

-- :name edit-task :!
-- :doc edit mistakes on tasks
update todo set task = :newtask where task = :oldtask

-- :name delete-all :!
-- :doc delete all tasks
delete from todo