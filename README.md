# StaffChat
> A simple and lightweight staff chat plugin for Spigot servers.

[![StaffChat](https://github-readme-stats.vercel.app/api/pin/?username=NerdyTechy&repo=StaffChat&count_private=true&show_icons=true&theme=dark)](https://github.com/NerdyTechy/StaffChat)

## Developer API
To make use of the Developer API, simply add the jar file as a dependency (Yes, it's the same one as you use on your server) and create a new event listener, the same way you would with any other Spigot event.

The events available to use are:
* `StaffChatMessageSent`
* `AdminChatMessageSent`
* `DeveloperChatMessageSent`
* `BuildChatMessageSent`

### Example Code
```java
@EventHandler
public static void onStaffChatMessage(StaffChatMessageSent e){
  String msg = e.getMessage();
  CommandSender sender = e.getSender();
  
  sender.sendMessage("You just sent a staff chat!");
}
```
All events are cancellable.

## License
This project is licensed under an MIT license. Feel free to fork this project, or use some/all of it's code in a commercial environment.
