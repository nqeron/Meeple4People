package core.MainEntryPoint;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.noahfields.DAO.DesignerDAO;
import com.noahfields.DAO.GameDAO;
import com.noahfields.DAO.MechanicDAO;
import com.noahfields.DAO.OracleConnection;
import com.noahfields.Models.Designer;
import com.noahfields.Models.Game;
import com.noahfields.Models.Mechanic;

public class MainEntryClass {

	private static Scanner in;
	public static void main(String[] args) {
		
		GameDAO gameDao = new GameDAO();
		List<Game> games = gameDao.searchForGames("Viticulture", null, -1, -1, -1, -1, null, null);
		System.out.println(games.toString());
		//in = new Scanner(System.in);
		
		//in.close();
//		try {
//			Connection conn = new OracleConnection().getConnection();
//			
//			if (conn.isValid(10)) {
//				System.out.println("Connected!");
//			}
//			
//			conn.close();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		

	}
	
	//login
	
	//register to be a user
	
	public static void menu() {
		System.out.println("What would you like to do?");
		System.out.println("1. List the next six recommended boardgames");
		System.out.println("2. Search for a board game");
		System.out.println("3. Add a board game to my cart");
		System.out.println("4. Checkout");
		System.out.println("5. Return a game");
	}

	public static void searchForGame() {
		System.out.println("Enter in the name of the game to search for");
		String name = in.nextLine();
		System.out.println("Enter in the years to search for (separated by commas)");
		
		//Parse the years into integer form
		String yearsLine = in.nextLine();
		String[] yearsStr = yearsLine.split(",");
		int[] yearsInt = new int[yearsStr.length];
		for(int i = 0; i < yearsStr.length; ++i) {
			yearsInt[i] = Integer.parseInt(yearsStr[i]);
		}
		
		System.out.println("Enter in the lowest game cost");
		double lowCost = in.nextDouble();
		in.nextLine();
		
		System.out.println("Enter in the highest game cost");
		double highCost = in.nextDouble();
		in.nextLine();
		
		System.out.println("Enter in the names of the Designers to search by, (e.g. Rosenberg, Uwe: Stegmeier, Jamie) :");
		String designersLine = in.nextLine();
		String[] designers = designersLine.split(": ");
		String[][] designerNames = new String[designers.length][2];
		for(int i = 0; i < designers.length; ++i) {
			designerNames[i] = designers[i].split(", ");
		}
		
		DesignerDAO designerDao = new DesignerDAO();
		List<Designer> designerIds = designerDao.getDesignersByNames(designerNames);
		
		System.out.println("Enter in the names of the Mechanics to search by, separated by commas");
		String[] mechanicNames = in.nextLine().split(", ");
		
		MechanicDAO mechanicDao = new MechanicDAO();
		List<Mechanic> mechanics = mechanicDao.getMechanicsByName(mechanicNames);
		
	}
}
