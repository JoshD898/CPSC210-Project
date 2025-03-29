# CPSC 210 Project - Simple Art Gallery

### Project Description

The application will function as a basic art gallery. Users will be able to create and edit drawings, then publish those drawings to their personal gallery. The intended audience is people interested in experimenting with digital art.

This project interests me because I enjoy drawing, and I believe it will be a great way to improve my Java skills.

*Note: I think that implementing an interface that allows users to draw may be beyond the scope of this project. I may attempt it, but initially, I will simplify the process by representing a "drawing" as a rectangle. Users will be able to modify the color and size of the rectangle.*

### Phase 1 User Stories:
- As a user, I want to be able to add a drawing to my gallery while specifying the title, color and shape.
- As a user, I want to be able to change a drawing status one-directionally from *in progress* to *complete*.
- As a user, I want to be able to edit drawings that are *in progress*.
- As a user, I want to be able to remove drawings from my gallery.
- As a user, I want to be able to view all drawings in my gallery, and also filter by drawing status.
- As a user, I want to be able to see a summary of the number of drawings in my gallery, including a breakdown by project status.

### Phase 2 User Stories:
- As a user, I want to have the option to save my current gallery to file when I quit the application.
- As a user, I want to be able to load, save and delete multiple save files.

### Phase 3 User Stories:
- As a user, I want to be able to load and save the state of the application from the GUI.
- As a user, I want to be able to view, add, edit and delete my gallery drawings from the GUI.

### Instructions for End User:
- You can add a new drawing to the gallery by clicking the `Add` button and specifying the drawing properties.
- You can select a drawing by clicking on it. The background will change to grey when selected.
- You can modify the properties of the selected drawing by clicking the `Edit` button.
- You can delete the selected drawing by clicking the `Delete` button.
- You can locate my visual component by navigating to `./res/LoadingScreen.png`.
- You can save the state of the application by clicking the `Save` button.
- You can reload the state of the application by clicking the `Load` button.

### Phase 4 Example of Logging Output:

```
Gallery loaded from file     |     Sat Mar 29 15:17:24 PDT 2025
Added drawing: Sunset     |     Sat Mar 29 15:17:24 PDT 2025
Added drawing: Waves     |     Sat Mar 29 15:17:34 PDT 2025
Modified drawing: Waves     |     Sat Mar 29 15:17:38 PDT 2025
Removed drawing: Sunset     |     Sat Mar 29 15:17:42 PDT 2025
Gallery saved to file     |     Sat Mar 29 15:17:43 PDT 2025
```

### Phase 4 Refactoring Ideas:

Refactor 1:

The `TerminalApp` and `UserInterface` classes share numerous fields and methods. To minimize code duplication, an abstract class could be introduced to centralize shared fields such as `gallery` and `selectedDrawing`, along with shared methods for adding, modifying, and managing drawings, as well as handling file operations like loading and saving. Both the `TerminalApp` and `UserInterface` classes could then extend this abstract class, reducing the ammount of repeated code.

Refactor 2:

A `UserInterface` object is currently passed as an argument to several user interface helper classes (i.e. `EditPanel`), which then invoke methods on it to modify the `selectedDrawing` or change what panel is displayed. To simplify the code and improve manageability, the singleton pattern could be applied within the `UserInterface` class. This would eliminate the need to pass the `UserInterface` as an argument, making the code cleaner and easier to maintain.