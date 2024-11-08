# :iphone: Instagram Tool :iphone:

Many Instagram users are curious about who is following them back. This tool allows users to run a script locally to easily determine this information.

## Instructions

### Step 1:
1. Login to your Instagram account and go to settings
2. Go to the Account Center
3. Under Account Settings select "Your information and permissions"
4. Select "Download your information"
5. Select "Some of your information"
6. Scroll down to Connections and select "Followers and Following"
7. Select "Download to device"
8. Under Date Range select "All time" and for Format select "JSON"
9. Finally, click "Create files" and download the data when it's ready

### Step 2:
1. Unzip the file and there will be 2 files needed: "followers_1.json" and "following.json"
2. Run the Python script, it will request user input in the terminal
3. Paste the file path for the corresponding followers_1 and following file when asked
4. Finally, the results are written to a .txt file in the CWD

### Tutorial for Installing Python if Needed
[Windows](https://www.geeksforgeeks.org/how-to-install-python-on-windows)
[MacOS](https://www.geeksforgeeks.org/how-to-download-and-install-python-latest-version-on-macos-mac-os-x)

