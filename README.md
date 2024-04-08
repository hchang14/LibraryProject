#  Book Management Application W2024T2

## Project Description:
The Book Management Application is a Java-based desktop application designed to facilitate the management of book adding, removing, and searching within a library. The application offers the following main features:

***1. Management:*** Users can add, delete, and view book information in the library, including title, author, and category.

***2. Viewing all books:*** Users can check the list of all books which have been added to the library.

***3. Searching by author by category:*** The application provides search functionality, allowing users to find books by category or author. Allowing user discover books in specific areas of interest.

### Target Users:
The Book Management Application is primarily designed for individuals who need to efficiently manage their book collection, as well as search for books of interest.
### Why This Project Is of Interest:
This project is of interest to me as it combines my passion for Java programming with my appreciation for libraries and reading. By developing this application, I aim to enhance my programming skills while providing a practical tool for book management. Additionally, I believe this application will be valuable for individuals who want to efficiently manage book adding, removing and searching within their personal libraries.

## User Stories:
- As a user, I want to be able to add a new book to the library's collection, including specifying the title, author, and category.
- As a user, I want to be able to view the total collection of books in the library.
- As a user, I want to be able to view book(s) with a specific category, so that user can assist patrons in finding books they are interested in.
- As a user, I want to be able to view book(s) with a specific author, so that user can assist patrons in finding books they are interested in.
- As a user, I want to be able to remove a book from the library's collection, so that outdated or damaged books can be taken out of circulation.
- As a user, I want to be able to be given the option to save my library list to file.
- As a user, I want to be able to be given the option to load my library list from file when I start the application.

## Instructions for Grader
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking the "Add a new book" button in the homepage. 
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking the "Remove existing book(s)" button in the homepage.
- You can locate my visual component (the background of the start page) by running the application, it is in the start page. And I stored the picture under the data package which named "phase3_pic2.png".
- By clicking the "Quit" button, a pop-up window appears. You can save the data of my library by clicking the "Yes" button in that pop-up window.
Meanwhile, you can choose "no"-- do not save data or "cancel"-- disappear the pop-up window.
- You can reload the data of your library by clicking the "Yes, load data" button at the start page.
- You can create a new library without any saved data by clicking "no, create a new one" button in the start page.

## Phase4: Test2
Saving data...
Data saved successfully.
Sun Apr 07 22:39:07 PDT 2024
Loaded previous book(s).

Sun Apr 07 22:39:07 PDT 2024
Book: Title: power of now, Author: eckhart tolle, Category: spritual was added.

Sun Apr 07 22:39:07 PDT 2024
Book: Title: Seven, Author: Susan, Category: Life was added.

Sun Apr 07 22:39:07 PDT 2024
Book: Title: Heart, Author: Zandy, Category: Science was added.

Sun Apr 07 22:39:07 PDT 2024
Book: Title: Bron with red, Author: Owen, Category: Science was added.

Sun Apr 07 22:39:07 PDT 2024
Book: Title: 3, Author: 4, Category: 5 was added.

Sun Apr 07 22:39:07 PDT 2024
Book: Title: Love wins all, Author: IU, Category: Romantic was added.

Sun Apr 07 22:39:14 PDT 2024
Book: Title: 3, Author: 4, Category: 5 was removed.

Sun Apr 07 22:39:49 PDT 2024
Book: Title: GOOD, Author: James, Category: Arts was added.

Sun Apr 07 22:39:53 PDT 2024
saved existing book(s).

## Phase4: Test3
If I had more time to work on the project, one refactoring I might consider 
is introducing more interfaces to improve the flexibility and maintainability 
of the design. For example, I could create interfaces such as LibraryListener 
to decouple the Library class from its listeners, allowing for easier addition 
of different types of listeners in the future. By using interfaces, I can also 
promote code reuse and make the system more extensible.
