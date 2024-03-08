# Java GUIs Program Project Part 2

**Course**: INFO-I 311, Application Development, Fall 2023 <br>
**Institution**: Indiana University <br>
**Final Product**: See [Screenshots of Final Product](#screenshots-of-final-product) at the bottom of this page.
> Imported from my IU enterprise account github.iu.edu/lsgulsen

## Project Overview

In Part 2 of this Java GUIs Program, building upon the solid foundation laid out in Part 1, we are set to introduce 
significant enhancements
aimed at streamlining inventory management and enhancing customer experience with precise order fulfillment projections. 
The enhancements are designed to ensure seamless operation, utilizing real-time system data to optimize inventory tracking 
and order management.
> For more information, course assignment requirements, and detailed documentation, please visit the [project Wiki](https://github.com/leyla-gulsen/I311_project_part2/wiki).

### Learning Objectives
- Understand the fundamentals of the Java language
- Write your own fully functional GUI applications in Java, using Eclipse
- Be familiar with the GIT environment and understand version control
- Understand task allocation and time management in a small team environment
- Implement JUnit testing throughout the program

## Technologies and Tools Used

- Java
- JUnit
- SceneBuilder
- Eclipse
- Git

## Features and Functionality

### Inventory Management
- **Inventory Class**: A new, dynamic Inventory class will be introduced to monitor and update the stock levels as
orders are fulfilled or as new shipments arrive at the warehouse. This class will be crucial in managing both current
stock and scheduled inventory restocks, enabling the system to provide accurate backorder estimates and projected fill
dates.
- **Automated Inventory Updates**: The Inventory class will automatically adjust inventory levels based on real-time
activities, such as the filling of orders and the receipt of new stock, ensuring that inventory data is always current
and reliable.
- **Projected Fill Dates**: Leveraging the Inventory class, the system will now calculate and display projected fill
dates for orders, particularly useful for items on backorder, enhancing customer communication and satisfaction.
### Order Enhancement
- **Projected Fill Date Field**: The Order class will be expanded to include a field for the projected fill date,
offering customers a transparent view of when they can expect their orders to be fulfilled.
### GUI Improvements
- **Real-Time Inventory Display**: The GUI will feature a dedicated section for monitoring current inventory levels,
neatly categorized by size and color, providing a comprehensive overview of stock availability.
- **Inventory Replenishment Interface**: A new function within the GUI will allow users to schedule the addition of
new Thneeds to the warehouse inventory, complete with arrival dates to ensure accurate future stock level projections.
- **Dynamic Inventory and Order Updates**: As the system tracks real-time changes, the GUI will reflect adjustments in
inventory and order statuses accordingly, maintaining an up-to-date snapshot of operations.
### Reporting Tools
- **Order Fulfillment Reports**: The system will generate detailed reports analyzing the efficiency of order fulfillment,
helping to identify trends in how quickly orders are processed and dispatched.
- **Inventory and Demand Insights**: Through data analysis, reports will reveal the most and least popular items, pinpoint
items frequently backordered, and highlight top customers, offering valuable insights for inventory management and
marketing strategies.
- **Strategic Planning Support**:  By dissecting past performance and customer behavior, the generated reports will
serve as a powerful tool for strategic planning, assisting in inventory control and marketing efforts to better align
with customer demand.
### Collaborative Development Focus
- **Specialized Roles**: Team members will assume lead roles in developing the Inventory and Order classes, GUI
enhancements, and the new reporting features, ensuring a well-rounded and robust system upgrade.
- **Comprehensive Testing and Documentation**: Emphasis will be placed on rigorous testing, including unit
tests for new functionalities and integration tests for the system as a whole, alongside meticulous documentation
to maintain code integrity and facilitate future enhancements.

## Challenges and Learnings

The biggest challenge for me was implementing the File I/O class. This was my first project using Git within a group, 
and it was difficult knowing when and where to enable the File I/O class along with the other classes of Order and Customer, 
all without interrupting each class' independent functionality.

## Final Outcome

The project met all of the course requirements and is a fully functional Java GUI progam. It was a valuable learning 
experience working with a team within a Git interface. It was also exciting learning Java while also seeing the program 
come to life within the GUI created in SceneBuilder.

## Acknowledgments
Professor J Duncan for teaching the course and creating a project that was a great learning experience, and Teaching 
Assistants Andy Myers, Erika Becker, and Jeff Hochgesgang for guidance and insights.

## Screenshots of Final Product
