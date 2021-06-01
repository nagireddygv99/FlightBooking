package flight_booking_assign;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;
import java.io.InputStreamReader;

public class App {
	
	
	//verifying the new register for existing
	public static boolean verityInfoOfNewRegister(List<UserData> user,String phNum) {
		
		for(UserData d:user) {
			if(d.getMobNum().equals(phNum)) return false;
		}
		return true;
		
		//return user.stream().filter(x->x.getMobNum() == phNum).collect(Collectors.toList()).size()==0;
	}	
	
	
	public static Journey bookTicket(ListOfFlights flight) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String name, age, flightName, from, to, startTime, endTime, price;
		try {
			System.out.println("enter name:");
			name = br.readLine();
			System.out.println("enter age:");
			age = br.readLine();
			flightName = flight.getFlightName();
			from = flight.getFrom();
			to = flight.getTo();
			startTime = flight.getStartTime();
			endTime = flight.getEndTime();
			price = flight.getPrice();
			Journey journey = new Journey(name, age, flightName, from, to, startTime, endTime, price);
			System.out.println("booking your ticket...");
			try {
				Thread.sleep(1000);
				System.out.println("ticket booked successfully...!\n");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return journey;
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error occured, try again...");
			return null;
		}
		
	}
	
	public static UserData userRegistration(List<UserData> userInfo) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String name,mobNum,govId,dob,email,password;
		try {
			
			System.out.println("enter name:");
			name = br.readLine();
			System.out.println("enter mobilenumber:");
			mobNum = br.readLine();
			System.out.println("enter govidNum:");
			govId = br.readLine();
			System.out.println("enter dob(DD/MM/YYYY):");
			dob = br.readLine();
			System.out.println("enter emailId:");
			email = br.readLine();
			System.out.println("enter Password:");
			password = br.readLine();
			
			if(verityInfoOfNewRegister(userInfo,mobNum)) {
				return new UserData(name,mobNum,govId,dob,email,password);
			}
			else {
				System.out.println("you are already a user\nPlease try login");
			}
		} catch(IOException e) {
			System.out.println("Invalid entry..");
		}
		return null;
	}

	
	
	
	
	// login the user
	
	private static int loginUser(List<UserData> userInfo,String mobNum, String password) {
		
		for(int i=0;i<userInfo.size();i++) {
			if(userInfo.get(i).getMobNum().equals(mobNum) && userInfo.get(i).getPassword().equals(password)) return i;
		}
		System.out.println("Not valid user\nTry register....");
		return -1;
	}
	
	
	
	
	// main method logic...
	
	
	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		List<UserData> userInfo = new ArrayList<>();
		
		List<ListOfFlights> allFlights = new ArrayList<>();  
		
		allFlights.add(new ListOfFlights("indigo","hyderabad","vijayawada","12:20","4:30","2500"));
		allFlights.add(new ListOfFlights("indio","hyderabad","vijayawada","10:00","5:00","3800"));
		allFlights.add(new ListOfFlights("airways","chennai","bangalore","8:40","3:20","2000"));
		allFlights.add(new ListOfFlights("kingfisher","bangalore","hyderabad","19:45","23:50","3500"));
		allFlights.add(new ListOfFlights("truejet","hyderabad","delhi","14:25","22:50","14000"));
		allFlights.add(new ListOfFlights("goIndia","chennai","hyderabad","4:00","10:30","3800"));
		allFlights.add(new ListOfFlights("kingfisher","hyderabad","goa","15:20","23:40","9000"));
		
		int choice  = 0;
		do {
			System.out.println("\n1.Register\n2.Login\n3.Exit");
			try {
				choice = Integer.parseInt(br.readLine());
			} catch (NumberFormatException | IOException e) {
				System.out.println("Invalid entry\nenter valid choice..");
				continue;
			}
			switch(choice) {
			case 1: {
				
				UserData adding = userRegistration(userInfo);
				if(adding != null) {
					userInfo.add(adding);
				}
			}
			break;
			case 2: {
				String mobNum,password;
				try {
					System.out.println("enter mobilenumber:");
					mobNum = br.readLine();
					System.out.println("enter password:");
					password = br.readLine();
					int index = loginUser(userInfo,mobNum,password);
					if(index != -1) {
						int userChoice = 0;
						do {
							System.out.println("\n1.Book Ticket\n2.History\n3.Exit");
							try {
								userChoice = Integer.parseInt(br.readLine());
								switch(userChoice) {
								case 1: {
									int i=1;
										String from,to;
										System.out.println("From...");
										from = br.readLine().toLowerCase();
										System.out.println("To");
										to = br.readLine().toLowerCase();
										boolean noFlight = true;
										List<ListOfFlights> availbleFlights = new ArrayList<>();
									for(ListOfFlights list:allFlights) {
										if(list.getFrom().equals(from) && list.getTo().equals(to)) {
											System.out.println(i++ +"-->"+ list);
											availbleFlights.add(list);
											noFlight = false;
										}
									}
									if(noFlight) {
										System.out.println("no flights found in the route?");
										continue;
									}
									
									int flightChoice= Integer.parseInt(br.readLine());
									if(flightChoice>0 && flightChoice<=availbleFlights.size()) {
										Journey userJourney = bookTicket(availbleFlights.get(flightChoice-1));
										userInfo.get(index).setFlightBookings(userJourney);
									}
									else {
										System.out.println("entry wrong...");
										continue;
									}
									
								}
								break;
								case 2: {
									if(userInfo.get(index).getFlightBookings().size()==0) {
										System.out.println("no recent booking....!");
										continue;
									}
									for(int i=0;i<userInfo.get(index).getFlightBookings().size();i++) {
										System.out.println(userInfo.get(index).getFlightBookings().get(i));
									}
								}
								break;
								case 3:	System.out.println("Logging you out..."); 
									break;
										
								default: System.out.println("invalid entry made...");
									break;
								}
							}
							catch(IOException e) {
								System.out.println("Invalid entry"); 
								continue;
							}
						}while(userChoice != 3);
					}
					
				} catch(IOException e) {
					
				}
			}
			break;
			case 3: System.out.println("Exiting...");
				break;
			default: System.out.println("Invalid choice");
			}
			
		}while(choice != 3);
		
		
	}
 }
