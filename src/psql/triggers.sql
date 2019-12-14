CREATE OR REPLACE FUNCTION dish_average()
 RETURNS trigger
     SET SCHEMA 'public'
    LANGUAGE plpgsql
	AS $body$
DECLARE
   counter    real := 1;
   counter2    real := 1;
 BEGIN
	select sum(grade) into counter from dish_grades where id_dish = new.id_dish;
	select count(grade) into counter2 from dish_grades where id_dish = new.id_dish;
	update dish set grade = counter/counter2 where id=new.id_dish;
	RETURN NEW;
 END;
 $body$;

CREATE TRIGGER dish_grades_Average AFTER INSERT ON dish_grades
FOR EACH ROW
EXECUTE PROCEDURE dish_average();

CREATE OR REPLACE FUNCTION account_average()
 RETURNS trigger
     SET SCHEMA 'public'
    LANGUAGE plpgsql
	AS $body$
DECLARE
   counter    real := 1;
   counter2    real := 1;
 BEGIN
	select sum(grade) into counter from dish_grades where id_account = new.id_account;
	select count(grade) into counter2 from dish_grades where id_account = new.id_account;
	update account set grade = counter/counter2 where id=new.id_account;
	RETURN NEW;
 END;
 $body$;

 CREATE TRIGGER account_grades_Average AFTER INSERT ON account_grades
FOR EACH ROW
EXECUTE PROCEDURE account_average();