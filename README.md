<img src="https://thatcakeid.com/assets/images/osthmlogo.png" alt="os-thm Logo" width="200"/>

 # os-thm
 Stable version: 3.0.2
 Development version: (Not planned yet)

 Written by members of ThatCakeID: [リェンーゆく](https://github.com/ryenyuku), and [Iyxan23](https://github.com/Iyxan23).
 Some app's UI design & theme made by: [thatcakepiece](https://github.com/thatcakepiece).

 os-thm _(stands for: **o**pen**s**ource-**th**e**m**e)_ is a piece of software that can manage, edit, import, export or remove theme. One of the os-thm's key feature is that the theme can be applied globally, means the theme can used by apps that have implemented os-thm.
 os-thm is intended to be open-source, rich, and easy to use.

 #### So, let's get started!

 ## For Consumers
 os-thm is an easier way to change your theme globally for every single app that has implemented os-thm with a single click of a button.

 Make sure to have the os-thm Manager app installed, as it's supposed to be the manager for all os-thm themes. You can [download it here](https://github.com/ThatCakeID/os-thm-android/releases), or [here](https://os-thm.thatcakeid.com).

 ## For Developers
 os-thm is an easier way to make theme accessible to the user without worrying about managing themes, all of that _(adding, importing, removing, editing themes)_ are managed by os-thm, and the best part is, it's [open source](https://github.com/ThatCakeID/os-thm-android)!

 ### How to implement os-thm to your app?

 _(Note: for sketchware developers, you might want to use [os-thm-sketchware](https://github.com/ThatCakeID/os-thm-sketchware) [Currently not developed yet])_

 Add os-thm implementation to your build.gradle
 ```gradle
 implementation 'tw.osthm:3.0.1'
 ```
 That's it.

 ### How to use the library?
 How to apply the current applied theme with os-thm
 ```java
 OsThmTheme currentTheme = osthmEngine.getCurrentTheme();
 myFab.setBackgroundColor(currentTheme.colorAccent);
 rootView.setBackgroundColor(currentTheme.colorBackground);
 etc...
 ```
 We are currently finding solutions for applying theme to be less tedious, and easier.

 That's it!

 ## Colors meanings

 Here is a table showing the meanings of the colors:

 |Color Name             |Color Meaning                                                                   |
 |-----------------------|--------------------------------------------------------------------------------|
 |colorPrimary           |The primary color for your app (ex: actionBar)                                  |
 |colorPrimaryDark       |The darker primary color for your app (ex: statusbar)                           |
 |colorAccent            |The accent color for your app (ex: Button & FAB)                                |
 |colorStatusbarTint     |The tint color of the statusbar icon (can only be black[0] and white[1])        |
 |colorBackground        |The background color for the root container                                     |
 |colorBackgroundText    |The text color for TextView(s) that's inside the root container                 |
 |colorAccentText        |The text or tint color for the button/FAB's image source                        |
 |shadow                 |The boolean value to determine if shadow is enabled or not (can only be 1 and 0)|
 |colorControlHighlight  |The ripple color                                                                |
 |colorHint              |The color for the EditText hint                                                 |
 |colorPrimaryTint       |The color for the ActionBar icons or items                                      |
 |colorBackgroundTint    |The tint color for images in a root container                                        |
 |colorBackgroundCard    |The background color for the card in a root container                           |
 |colorBackgroundCardTint|The color tint for an ImageView in a card in a root container                   |
 |colorBackgroundCardText|The TextView color in a card inside a container that's in the root container    |
 |colorPrimaryCard       |The background color for a card inside an appBar                                |
 |colorPrimaryCardTint   |The color tint for an ImageView in a card inside an appBar                      |
 |colorPrimaryCardText   |The TextView color in a card that is inside of an appBar                        |
 |colorDialog            |The background color for the root dialog container                              |
 |colorDialogText        |The text color for TextView(s) that's inside the dialog root container          |
 |colorDialogTint        |The tint color for images in a dialog root container                            |


 If you didn't understand the table above, you can use the Theme Editor on os-thm Theme Manager to determine which colors points to which.

### Branches:
 - master: Current latest and stable version of os-thm-android.
 - dev: Current canary branch with newest features, bug patches, and still in development state.

 Documentation written by [Iyxan23](https://github.com/Iyxan23) and slightly edited by [リェンーゆく](https://github.com/ryenyuku)

 (c) Copyright 2020 [ThatCakeID](https://github.com/ThatCakeID)

