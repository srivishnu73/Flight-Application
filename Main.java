import java.util.*;
class flight
{
    int flightNo;
    int flightCapacity;
    int avaiableSeat;
    int economicSeat;
    int businessSeat;
    int avaiableSeatinE;
    int avaiableSeatinB;
    String flightStatus;
    
    flight(int fno,int fcap,String flstatus){
        flightNo=fno;
        flightCapacity=fcap;
        avaiableSeat =fcap;
        economicSeat=fcap/2;
        businessSeat=fcap-economicSeat;
        avaiableSeatinE=economicSeat;
        avaiableSeatinB=businessSeat;
        flightStatus=flstatus;
    }
    
    int getFlightNo(){
        return flightNo;
    }
    int getFlightCapacity(){
        return flightCapacity;
    }
    int getAvaiableSeat(){
        return avaiableSeat;
    }
    int getAvaiableSeatInB(){
        return avaiableSeatinB;
    }
    int getAvaiableSeatInE(){
        return avaiableSeatinE;
    }
    String getFlightStatus(){
        return flightStatus;
    }
    
    void alterFlightStatus(String newStatus){
        flightStatus=newStatus;
    }
    static void alterSeatsInB(int remSeat,int flightNo){
         ArrayList<flight> flightList=Main.getArrayFlight();
            for(flight x:flightList){
                if(x.flightNo==flightNo){
                  x.avaiableSeatinB=remSeat;
                  System.out.println("Bussnees class suscessfully booked");
                     
            }
        }
        
    }
      static void alterSeatsInE(int remSeat,int flightNo){
         ArrayList<flight> flightList=Main.getArrayFlight();
            for(flight x:flightList){
                if(x.flightNo==flightNo){
                  x.avaiableSeatinE=remSeat;
                  System.out.println("Economic class sucessfully booked");
                     
            }
        }
    }
    static void displayFlight(ArrayList<flight> flightList){
        System.out.println("FlightNo  avaiableInBuss avaiableInEco  FlightStatus");
        	for(flight x:flightList){
		    System.out.println(" "+x.getFlightNo()+"\t\t"+x.getAvaiableSeatInB()+"\t\t"+x.getAvaiableSeatInE()+"\t"+x.getFlightStatus());
		}

    }
    
  
    
}

class Admin
{
    static void Admin(){
        Main.clearScreen();
        Scanner obj =new Scanner(System.in);
        System.out.println("Enter The Admins UserId");
        String adminId=obj.next();
        System.out.println("Enter The Admins Password");
        String adminpass=obj.next();
        if((adminId.equals("admin"))&&(adminpass.equals("admin"))){
            System.out.println("hello admin.........Starting admin Menus");
             try{
                Main.loading("Loading");
                }catch(Exception e){            // loading ----
                System.out.println();
                }
            System.out.println();
            System.out.println("Welcome to admin menu");
            adminMenu();
        }
        else{
            System.out.println("Invalid admin Id Or Pass...\n\n Please enter the valid Id Pass");
            if(Main.toContinue()){
                Admin();
            }
            else{
                Main.clearScreen();
                Main.displayMenu();
            }
        }
    }
    static void adminMenu(){
        System.out.println();
        Scanner obj =new Scanner(System.in);
        System.out.println("1.Passger Details\n2.Cancel Flight\n3.Booking Log\n4.Exit");
        int adminInt =obj.nextInt();
        switch(adminInt){
            case 1:
                booking.displayPassList();
                adminMenu();
                break;
            case 2:
                Main.cancelFlight();
                adminMenu();
                break;
            case 3:
                bookingLog.DisplayBookingLog(Main.getBookingLog());
                Main.pressContine();
                adminMenu();
                break;
            case 4:
                Main.startruning();
        }
    }
    
}

class booking
{
    String passName;
    int noTicket;
    Boolean eOrB;
    int flightNo;
    
    booking(){
        Scanner obj =new Scanner(System.in);
        System.out.println("passName:");
        passName=obj.next();
        System.out.println("FlightNo:");
        flightNo=obj.nextInt();
        
        ArrayList<flight> flightList=Main.getArrayFlight();
            for(flight x:flightList){
                if(x.getFlightNo()==flightNo&x.getFlightStatus()!="Avaliable"){
                   System.out.println("Sorry The flight you Have selected Not Avaliable");
                   new booking();
            }
        }

        System.out.println("BussinessClass(true) Or EconomicClass(false)");
        eOrB = obj.nextBoolean();
        getNoTicket();
    }
    
    void getNoTicket(){
        Scanner obj =new Scanner(System.in);
        System.out.println("enter No Of Tickets");
        noTicket=obj.nextInt();
        
        if(Main.checkAvaialty(flightNo,noTicket,eOrB)){
            System.out.println("Tickets Are Avaliable");
            startBooking(flightNo,noTicket,eOrB);
        }
        else 
        {
            System.out.println("Sorry ..Tickets Are Not Avaliable");
        }
    }
    static void startBooking(int flightNo,int noTicket,Boolean eOrB){
        if(eOrB==true){
             ArrayList<flight> flightList=Main.getArrayFlight();
            for(flight x:flightList){
                if(x.flightNo==flightNo){
                  flight.alterSeatsInB(x.avaiableSeatinB-noTicket,flightNo);
                     
            }
        }
        
    }
    else if(eOrB==false){
             ArrayList<flight> flightList=Main.getArrayFlight();
            for(flight x:flightList){
                if(x.flightNo==flightNo){
                  flight.alterSeatsInE(x.avaiableSeatinE-noTicket,flightNo);
                     
            }
        }
        
    }
    
    }
    static void displayPassList(){
        System.out.println("Pass List");
        //return 0;
    }
   

}

class bookingLog
{
    String passNameL;
    int noTicL;
    Boolean eOrBL;
    int flightNoL;
    String statusL;
    
    String getLogpassNameL(){
        return passNameL;
    }
    int getLognoTicL(){
        return noTicL;
    }
    Boolean getLogeOrBL(){
        return eOrBL;
    }
    int getLogFlightNo(){
        return flightNoL;
    }
    String getLogStatus(){
        return statusL;
    }
    
    bookingLog(String name,int fN,int noTi,Boolean eB,String Stat){
      passNameL=name;
      flightNoL=fN;
      noTicL=noTi;
      eOrBL=eB;
      statusL=Stat;
    }
    static void DisplayBookingLog(ArrayList<bookingLog> bookingLogList){
        System.out.println("PassengerName FlightNo NoOfTickect  FlightTicketType    FlightStatus");
        
        
        for(bookingLog x:bookingLogList){
            System.out.println("  "+x.getLogpassNameL()+"\t "+x.getLogFlightNo()+"\t     "+x.getLognoTicL()+"\t     "+((x.getLogeOrBL())?"BussinessClass":"EconomicClass")+"\t"+x.getLogStatus());
        }
        
    }
}
//===================================================Main============================================

class Main
{
    static ArrayList<flight> flightList= new ArrayList<flight>();
	static ArrayList<booking> bookingList = new ArrayList<booking>();
	static ArrayList<bookingLog> bookingLogList = new ArrayList<bookingLog>();
	
	static ArrayList getBookingLog(){
	    return bookingLogList;
	}
	static ArrayList getArrayFlight(){
	    return flightList;
	}
	static void cancelTicket(){
	    
	}
	 static void cancelFlight(){
         System.out.println("Enter the Flight No");
         Scanner obj =new Scanner(System.in);
         int No=obj.nextInt();
         
         ArrayList<flight> flightList=Main.getArrayFlight();
          for(flight x:flightList){
                if(x.flightNo==No){
                    System.out.println("The current status of the Flight is : "+x.getFlightStatus());
                }
            }
         System.out.println("Are sure want change the status of the flight");
         pressContine();
         for(flight x:flightList){
                if(x.flightNo==No){
                    x.alterFlightStatus((x.getFlightStatus()=="Avaliable")?"Canceled":"Avaliable");
                    System.out.println("The Status has been suscessfully changed");
                }
            }
         
    }
	public static void loading(String given) throws Exception {
        String anim= "|/-\\";
        for (int x =0 ; x < 100 ; x++) {
            String data = "\r" + anim.charAt(x % anim.length()) + " " + x+given+"...";
            System.out.write(data.getBytes());
            Thread.sleep(100);
        }
    }
	
    static  Boolean toContinue(){
        System.out.println("Press Y to continue with Booking Or N to canel");
        Scanner obj =new Scanner(System.in);
        char userIn=obj.next().charAt(0);
        if(userIn=='Y'||userIn=='y'){
        return true;
        }
        return false;
        
    }
     static Boolean checkAvaialty(int flightNo,int noTicket,Boolean eOrB){
        if(eOrB==true){
            for(flight x:flightList){
                if(x.flightNo==flightNo){
                    if(x.avaiableSeatinB>=noTicket){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        if(eOrB==false){
            for(flight x:flightList){
                if(x.flightNo==flightNo){
                    if(x.avaiableSeatinB>=noTicket){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return false;
    }
   
   static int displayMenu(){
       System.out.println();
       System.out.println(" 1.Get flight Details \n 2.Book fligth ticket \n 3.cancel Tickets  \n 4.admin Login");
        Scanner obj =new Scanner(System.in);
       return obj.nextInt();
   }
   static void pressContine(){
       System.out.println("Press Enterkey to contine");
       Scanner obj =new Scanner(System.in);
       obj.nextLine();
       clearScreen();
   }
   static void clearScreen(){
       System.out.print("\033[H\033[2J");  
       System.out.flush();
   }
   static void startruning(){
       int menu=displayMenu();
	    //flight.displayFlight(flightList);
	    switch(menu){
	    case 1:
	        flight.displayFlight(flightList);
	        pressContine();
	        startruning();
	        break;
	        
	    case 2:
	        //while(toContinue()){
	        flight.displayFlight(flightList);
	       bookingList.add(new booking());
	       // }
	       pressContine();
	       startruning();
	        break;
	    case 3:
	        cancelTicket();
	        break;
	    case 4:
	        Admin.Admin();
	        break;
	    }
   }
    
	public static void main(String[] args) {
	    
	    Scanner obj =new Scanner(System.in);
		flightList.add(new flight(1,16,"Avaliable"));
		flightList.add(new flight(2,16,"Avaliable"));
		flightList.add(new flight(3,16,"Avaliable"));
		flightList.add(new flight(4,16,"Canceled"));
		bookingLogList.add(new bookingLog("Pravin",1,3,true,"Booked"));
		System.out.println("Welcome To sri Aviation Ticket Booking Portal");
	    startruning();

	}

}
