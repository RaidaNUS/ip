# 🤖 Chatbot Name: ALO
- **Welcome to ALO, your very own chatbot assistant ^_^**
- **ALO helps you manage your daily tasks, deadlines, and events while keeping the experience light, friendly, and full of emojis!**

---

## 😍 Features
- **Task Management**: Add, delete, and list tasks.
- **Deadline & Event Tracking**: Keep track of due dates.
  - Input Dates and Time of Deadline and Event in **any format** you want!
  - Not limited to `23-03-16` or `24 march 2016` format.
    - Example: `deadline be happy /by as soon as possible` → Output in Command Line Interface: `[D][ ] be happy (by: as soon as possible)`
- **Task Completion Status**: Mark/Unmark tasks as done, shown with an `X`.
- **Persistent Storage**: Saves tasks even after restarting.
- **Search Function**: Find specific tasks easily.
- **Friendly Chat**: Enjoy interactive responses!
- **Emojis**: See cute and fun Emojis after every response!

---

## 🤩 Commands List
**Follow the commands to make your own Task List**

| Command                                          | Description                        | Example                             |
|--------------------------------------------------|------------------------------------|-------------------------------------|
| `todo <task>`                                    | Adds a to-do task                  | `todo Read book`                    |
| `deadline <task> /by <date/time>`                | Adds a deadline task               | `deadline Submit report /by Monday` |
| `event <task> /from <date/time> /to <date/time>` | Adds an event                | `event Meeting /from 3pm /to 5pm`   |
| `list`                                           | Lists all tasks                    | `list`                              |
| `mark <task_number>`                             | Marks a task as completed          | `mark 2`                            |
| `unmark <task_number>`                           | Marks a task as incomplete         | `unmark 2`                          |
| `delete <task_number>`                           | Deletes a task                     | `delete 1`                          |
| `find <keyword>`                                 | Finds tasks matching a keyword     | `find book`                         |
| `bye`                                            | Exits the chatbot                  | `bye`                               

>💡 Don't worry we will prompt you if the input has a mistake and show you the way to enter a task correctly


---
## 💬 Sample Inputs, CLI Outputs & File Format

Here are sample commands and how ALO responds in the CLI and what gets saved in `tasks.txt`.

---

### 📌 1. `todo buy milk`

**👉 Output in CLI**
> Aye Aye! I've added this task to the list: 
> \
>    [T][ ] buy milk 
> \
> Now you have 1 task in the list.

**📄 Saved in tasks.txt**
> T | | buy milk

### 📌 2. `deadline cs2100 hw /by 10 April`

**👉 Output in CLI**
>Take note of the deadline I've added to the task:
> \
>[D][ ] cs2100 hw (by:10 April) 
> \
> Now you have 2 tasks in the list.

**📄 Saved in tasks.txt**
>D | | cs2100 hw | 10 April

### 📌 3. `event internship interview /from 10am /to 12pm`

**👉 Output in CLI**

>I've added the event to the task list:
>\
>[E][ ] internship interview (from:10am to: 12pm)
>\
>Now you have 3 tasks in the list.

**📄 Saved in tasks.txt**
>E | | internship interview | 10am-12pm

### 📌 4. `mark 2`

**👉 Output in CLI**

>Cool! I've marked it as done! Congrats for finishing task:
>\
>[D][X] cs2100 hw (by: 10 April)

**📄 Saved in tasks.txt**
>D | X | cs2100 hw | 10 April

### 📌 5. `list`

**👉 Output in CLI**

Here ya are. This is your list. Enjoy!
> 1. [T][ ] buy milk
> 2. [D][X] cs2100 hw (by:10 April)
> 3. [E][ ] internship interview (from: 10am to: 12pm)

**📄 Saved in tasks.txt**
> T | | buy milk
> \
> D | X | cs2100 hw | 10 April
> \
> E | | internship interview | 10am-12pm


---
## 🛠 Installation
### **Prerequisites**
- **Java 11 or above** installed.  
  👉 [Download Java Here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

### **Download ALO**
1. Go to the [ALO GitHub Releases](https://github.com/RaidaNUS/ip/releases)
2. Download the latest `.jar` file.

### **Running ALO**
Run the chatbot using:
```sh
java -jar alo.jar
