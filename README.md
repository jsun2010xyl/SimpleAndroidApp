# SimpleAndroidApp

by Jingyan Sun

This is an independent Android project using Kotlin.

Android SDK 31

The application retrieves the data from the json file (https://fetch-hiring.s3.amazonaws.com/hiring.json).

After retrieving and processing the data, a single list of items is displayed to the users. The list
 - Filters out any item where the name is blank or null
 - Displays every item left grouped by "listId"
 - Displays every item left in ascending order (sorted first by "listId", then by "name")
