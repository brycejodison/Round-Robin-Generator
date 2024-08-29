import java.util.*;
import java.text.DecimalFormat;


public class pickleball
{
    static int count = 0;
    static int numCourts = 0;
    static int numPlayers = 0;
    
    static int[] offPlayers;
    //implement later!
    //add players that are off to a list
    //loop through those players FIRST at beginning of next iteration
    //when offplayers list is equal to size of original list(all players gotten off once), reset
    static int[] currPlayers;

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("00");

        System.out.print("Input total number of players: ");
        //input validation
        while(!(s.hasNextInt()))
        {
            System.out.print("# of Players must be an integer value: ");
            s.next();
        }
        numPlayers = s.nextInt();

        System.out.print("Input total number of courts: ");
        while(!(s.hasNextInt()))
        {
            System.out.print("# of Courts must be an integer value: ");
            s.next();
        }
        numCourts = s.nextInt();
        //Empty Space
        System.out.println("");

        int[] playerArray = new int[numPlayers];
        offPlayers = new int[numPlayers];
        for(int i = 1; i < playerArray.length + 1; i++)
        {
            playerArray[i - 1] = i;
        }

        boolean makingGames = true;
        //temporaries
        int time = 0; //change depending on when you start 
        int hour = 9;
        //temp array equal to values in playerArray 
        int[] tempArray = new int[numPlayers];
        for(int i = 0; i < playerArray.length; i++)
        {
            tempArray[i] = playerArray[i];
        }
        currPlayers = new int[numCourts * 4]; //4 players per court, curr players can never exceed size of amount of players in play
        while(makingGames)
        {
            //fill curr players with zeros
            for(int i = 0; i < currPlayers.length; i++)
            {
                currPlayers[i] = 0;
            }

            System.out.println(hour + ":" + df.format(time));
            for(int i = 0; i < numCourts; i++)
            {
        
                int playerOne =  findPlayer(tempArray, playerArray);
                addPlayer(currPlayers, playerOne);
                int playerTwo =  findPlayer(tempArray, playerArray);
                addPlayer(currPlayers, playerTwo);
                int playerThree =  findPlayer(tempArray, playerArray);
                addPlayer(currPlayers, playerThree);
                int playerFour =  findPlayer(tempArray, playerArray);
                addPlayer(currPlayers, playerFour);
                System.out.print("Court " + (i+1) + ": ");
                System.out.println(playerOne + " - " + playerTwo + " vs " + playerThree + " - " + playerFour);
            }
        
            //increment time
            time = time + 10; //10 minutes per game - change if games go longer
            if(time == 60)
            {
                hour++;
                time=0;
            }
            if (hour >= 11) //change value to determine how many games are made
            {
                makingGames = false;
            }

            //prints off players for the current games
            System.out.print("Off: " );
            for(int i = 0; i < playerArray.length; i ++)
            {
                boolean inPlay = false;
                if(playerArray[i] != 0)
                {
                    for(int j = 0; j < currPlayers.length; j++)
                    {
                        if(playerArray[i] == currPlayers[j])
                        {
                            inPlay = true;
                        }
                    }
                    if(!inPlay)
                    {
                        System.out.print(playerArray[i] + " ");

                    }
                    }
            }
            //Empty line
            System.out.println("");
            System.out.println("");
        }
        
        s.close();
        
    }//end of main

    //USING INDEX AS PLAYER! IF VALUE @ INDEX IS 0, PLAYER HAS BEEN USED
    public static int findPlayer(int[] arr, int[] mainArr)
    {
        int ranNum = 0;
        Random r = new Random();
        boolean checkSum = arraySummation(arr);
        //if summation of array is 0, all values are zero and thus have been used. Set tempArr to values present in mainArr for continued manipulation
        if(checkSum)
        {
            for(int i = 0; i < arr.length; i++)
            {
                arr[i] = mainArr[i];
            }
        }

        boolean searching = true;
        boolean inPlay = false;

        while(searching)
        {
            ranNum = r.nextInt(1, numPlayers + 1);
            //if number is not equal to 0: stop loop, set equal to zero
            //ranNum - 1 to prevent out of bounds
            inPlay = false;
            if(arr[ranNum - 1] != 0)
            {
                for(int i = 0; i < currPlayers.length; i++)
                {
                    if(ranNum == currPlayers[i])
                    {
                        inPlay = true;
                    }
                }
                if(!inPlay)
                {
                    searching = false;
                    arr[ranNum - 1] = 0;
                }
            }
        }
        return ranNum;
    }

    public static boolean arraySummation(int[] arr)
    {
        int total = -1;
        for(int i = 0; i < arr.length; i++)
        {
            total += arr[i];
        }
        if(total + 1 == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void addPlayer(int[] arr, int numPlayer)
    {
        if(count >= arr.length)
        {
            count = 0;
        }
        arr[count] = numPlayer;
        count++;
    }

    public static void fillArray()
    {
        for(int i = 0; i < currPlayers.length;i++)
        {
            currPlayers[i] = 0;
        }
    }

}  


