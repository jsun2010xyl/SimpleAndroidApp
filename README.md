# SimpleAndroidApp

by Jingyan Sun

This is an independent Android project using Kotlin.

Android SDK 31
MVVM
ROOM

The application retrieves the data from the json file (https://fetch-hiring.s3.amazonaws.com/hiring.json).

After retrieving and processing the data, a single list of items is displayed to the users. The list
 - Filters out any item where "name" is blank or null
 - Displays every item left grouped by "listId"
 - Displays every item left in ascending order by "name"
