import datetime
import firebase_admin
from firebase_admin import credentials, messaging
from datetime import date, timedelta
from google.auth import default
import pytz
import os
from datetime import datetime
from google.cloud import firestore
os.environ['GOOGLE_APPLICATION_CREDENTIALS'] = 'path/to/jsonfile'



# Initialize Firebase Admin SDK with your service account key
try:
     cred = credentials.Certificate("path/to/jsonfile")
     firebase_admin.initialize_app(cred)
except Exception as e:
     print("Firebase Admin SDK Error:", e)

# Check your database or server for scheduled workouts
# For example, if you are using Firebase Firestore


db = firestore.Client()


# # Query for workouts under user documents
def findScheduledWorkouts():
    users = db.collection("users")
    for user in users.get():
       scheduled_workouts = db.collection("users").document(user.id).collection("Scheduled Workouts").get()
       for workouts in scheduled_workouts:
        workout_date = convertDateTime(workouts.get("timestampField"))
        if(thirtyMinReminder(workout_date)):
            user_token = db.collection("users").document(user.id).get()
            sendNotification(user_token, workouts.id)

            print("User Uid: " + user.id)
            print("Name of Workout: "+ workouts.id)

fixed_current_datetime = datetime.now()
def thirtyMinReminder(scheduled_date):
    formatted_datetime = fixed_current_datetime.strftime("%B %d %Y %H:%M")

    current_parsed_datetime = datetime.strptime(formatted_datetime, "%B %d %Y %H:%M")
    scheduled_parsed_datetime = datetime.strptime(scheduled_date, "%B %d %Y %H:%M")

    # Create a datetime that is 30 minutes from now
    target_time = current_parsed_datetime + timedelta(minutes=30)
    # Check if the time difference is exactly 30 minutes
    if current_parsed_datetime <= scheduled_parsed_datetime <= target_time:
        return True
    else:
        return False


def sendNotification(user_token, workoutName):
    token =  user_token.get("Token")
    print("Token " + token)
    message = messaging.Message(
        notification=messaging.Notification(
            title="Workout Reminder",
            body="You have a {} scheduled for today!".format(workoutName),
        ),
        token=token,
        )
    messaging.send(message)


def convertDateTime(workoutDate):
    dt = datetime.fromisoformat(str(workoutDate))
    #formatted_date = dt.strftime("%B %d %Y %H:%M")
    # Define the Eastern Time Zone (EST)
    eastern_timezone = pytz.timezone('US/Eastern')
    # Convert 'dt' to Eastern Time
    dt_eastern = dt.astimezone(eastern_timezone)
    # Format the datetime as "Month Day Year Time" in Eastern Time (EST)
    formatted_date_eastern = dt_eastern.strftime("%B %d %Y %H:%M")
    return formatted_date_eastern


findScheduledWorkouts()