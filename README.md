# swipeomatic
This project mimicks the functionality of iOS where navigation can occur by swiping from left to right to move back in the navigation stack (the navigationController->interactivePopGestureRecognizer delegate). The difference in this code vs existing libraries like `https://github.com/ikew0ng/SwipeBackLayout` is the use of Fragments. So this project is an example of using multiple fragments inside a single Activity and allowing the user to swipe to navigate. It also maintains compatibility with hardware back button as well as toolbar / action bar home buttons.

# Main Concepts
1. If the app has floors and rooms, like a house, the floors are Activities and the rooms are Fragments. I prefer the use of Fragments over Avtivites due to greater stability I have seen over the course of developing many apps.
2. Smooth fragment transitions are provided by res/anim X, Y delta translations
3. A NavigationController interface handles callbacks from fragment UI and controls the logic to push new fragments
4. Yes you could achieve this out of the box with a FragmentPagerAdapter, however you have to do a lot of extra work to handle navigation logic to load and remove fragments based on user interaction (multiple exits from a given room).



