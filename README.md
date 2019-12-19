# Cloud_Simulations

The Simulations include the following design models for Cloud Architecture which follow Round Robin algorithm for Virtual Machine allocation.

  - TimeShared Cloudlet Scheduler with 1 Datacenter
  - TimeShared Cloudlet Scheduler with 2 Datacenters
  - SpaceShared Cloudlet Scheduler with 1 Datacenter
  - SpaceShared Cloudlet Scheduler with 2 Datacenters
  - Default Cloudlet Scheduler with 1 Datacenter
  - Default Cloudlet Scheduler with 2 Datacenters

## How to run the project

  - Make sure your device is sbt enabled.
  - Clone this private repository and open the sbt shell with the path as the local repository's path.
  - Type the command "sbt clean compile run" and execute it.
  - You will get a list of options for which file to run.
  - Enter the number corresponding to the file you want to run.
  - Done.

## How to test the project

  - Type the command "sbt clean compile run" and execute it.
  - Done.

#### Note
  - If logs don't appear in the sbt shell, you can import the project into IntelliJ and run directly in it.
  - For this, open IntelliJ, click on Open Project, select the project directory and open.
  - Let the project build via sbt and install all the external libraries.
  - Open the file you want to run in the src/main directory and run it.
  - Simulation results along with proper logging will appear in the output console.

## OUTPUT
The output shows the various results we obtain after the simulation. 

- Default Cloudlet Scheduler with 1 Datacenter
![Default Cloudlet Scheduler with 1 Datacenter](https://raw.githubusercontent.com/mehul-birari/Cloud-Simulators-using-CloudSim/master/images/DefaultDC1.JPG "Default Cloudlet Scheduler with 1 Datacenter")

- Default Cloudlet Scheduler with 2 Datacenters
![Default Cloudlet Scheduler with 2 Datacenters](https://raw.githubusercontent.com/mehul-birari/Cloud-Simulators-using-CloudSim/master/images/DefaultDC2.JPG "Default Cloudlet Scheduler with 2 Datacenters")

- Space Shared Cloudlet Scheduler with 1 Datacenter
![Space Shared Cloudlet Scheduler with 1 Datacenter](https://raw.githubusercontent.com/mehul-birari/Cloud-Simulators-using-CloudSim/master/images/SpaceSharedDC1.JPG "Space Shared Cloudlet Scheduler with 1 Datacenter")

- Space Shared Cloudlet Scheduler with 2 Datacenters
![Space Shared Cloudlet Scheduler with 2 Datacenters](https://raw.githubusercontent.com/mehul-birari/Cloud-Simulators-using-CloudSim/master/images/SpaceSharedDC2.JPG "Space Shared Cloudlet Scheduler with 2 Datacenters")

- Time Shared Cloudlet Scheduler with 1 Datacenter
![Time Shared Cloudlet Scheduler with 1 Datacenter](https://raw.githubusercontent.com/mehul-birari/Cloud-Simulators-using-CloudSim/master/images/TimeSharedDC1.JPG "Time Shared Cloudlet Scheduler with 1 Datacenter")

- Time Shared Cloudlet Scheduler with 2 Datacenters
![Time Shared Cloudlet Scheduler with 2 Datacenters](https://raw.githubusercontent.com/mehul-birari/Cloud-Simulators-using-CloudSim/master/images/TimeSharedDC2.JPG "Time Shared Cloudlet Scheduler with 2 Datacenters")

### Analysis
 
 - Default Scheduler:
    The simulation execution time gets divided by 2 when two datacenters are used in parallel, as compared to when one datacenter is used.
 
 - Space Shared Cloudlet Scheduler:
    The simulation shows how the cloudlets are scheduled 0, 8, 1, 9, 2, 10... when one datacenter is used.
    For the case when two datacenters are used, the cloudlets are scheduled serially that is 0, 1, 2, 3....
    This is because as cloudlet needs to be allocated they get a datacenter available immediately.
    
 - Time Shared Cloudlet Scheduler:
   Each cloudlet takes the same execution time.
   The only observable difference between the simulation results is that cloudlets get equally divided and allocated into the datacenters.

### Todos

 - Create more Datacenters and Brokers
 - Try MORE VM Allocation Policies
 - Write MORE Tests
  



   