Chiamate a stored procedure tramite jdbc

Esempi pratici:

*1
	La stored che intendiamo chiamare è una stored postgres che restituisce un parametro di OUT di tipo integer:
		
		get_somma(res OUT integer)
		
	la stored restituisce il numero di record presenti in una table Persona sul mio db postgres

N.B: Devono essere forniti tutti gli argomenti della procedura,sia quelli di IN che di OUT, siccome quelli di OUT non verranno valutati, è possibile fornire qualunque parametro dello stesso tipo, o null.

Codice: 	

    All'interno di un main method:
	
		 Connection con=null;

         try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DemoPOF", "postgres", "password");
			CallableStatement cstmt = con.prepareCall("CALL pof1.get_somma(?)");
			cstmt.setInt(1,1); 
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			System.out.println("somma: " + cstmt.getInt(1));
			}
		catch(Exception e){
            System.out.println(e.getMessage());
        }

*2		
	La stored che intendiamo chiamare ha due parametro uno di IN,di tipo integer, l'altro di OUT, di tipo varchar:

		get_amount5(id_p IN integer,nome_p OUT varchar)
		
	la stored restituisce il valore della colonna nome della table Persona (presente nel mio db) del record il cui id coincide con il param id_p	
	
Codice:
		
	All'interno di un main method:
			Connection con=null;
		try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DemoPOF", "postgres", "password");
		    CallableStatement cstmt = con.prepareCall("CALL pof1.get_amount5(?,?)");
            cstmt.setInt(1,5);
            cstmt.setString(2,"");
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.execute();
            System.out.println("nome: " + cstmt.getString(1));
			}
		catch(Exception e){
            System.out.println(e.getMessage());
        }
