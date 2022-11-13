POSTGRESQL
Stored Procedure

** Esempio stored procedure con parametro di OUT 
	
	create or replace procedure get_result(res OUT integer)
	language plpgsql
	AS $$
	declare
	begin
	select count(*) into res from nomeschema."nometable";
	end;
	$$
	
Per chiamare una procedure come quella sopra con parametro di OUT , occorre comunque passarle un input:

		call nomeschema.nomeprocedure(null); 
			
			oppure
			
		call nomeschema.nomeprocedure(2);

Il risultato di entrambe le chiamate è lo stesso: 1 riga con 1 colonna ("res") con valore pari al risultato della query.

Facciamo un altro esempio con procedure che ha sia parametri di IN che di OUT, la procedura è un caso reale implementato su mio db postgres e sulla table Persona dello schema pof1:
			
	create or replace procedure pof1.get_amount5(id_p IN integer,nome_p OUT varchar)
    language plpgsql
    as $$
    declare 
    somma integer;
    begin 
    select nome into nome_p from pof1."Persona" where id=id_p;
    end;
    $$
			
Effettuiamo la chiamata:
				
		call pof1.get_amount5(5,null);
			
			oppure
			
		call pof1.get_amount5(5,'');

			oppure
			
		call pof1.get_amount5(5,'op');

Il risultato delle tre chiamate è praticamente lo stesso: 1 riga 1 colonna ("nome_p") con valore pari al risultato della query.				
			