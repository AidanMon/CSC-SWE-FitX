import firebase_admin
from firebase_admin import credentials, messaging
from datetime import date
from google.auth import default
import os
os.environ['GOOGLE_APPLICATION_CREDENTIALS'] = './fitx-group9-33b3069bbf0f.json'

# try:
#     credentials, quota_project_id = default()
#     print("Project ID:", quota_project_id)

#     # You now have the credentials object that you can use with various Google services.
#     # For example, if you are using Google Cloud Storage:
#     from google.cloud import storage

#     client = storage.Client(credentials=credentials, project="fitx-group9")
#     # Use the client to interact with Google Cloud Storage.

# except Exception as e:
#     print("Error:", e)


# Initialize Firebase Admin SDK with your service account key
try:
    cred = credentials.Certificate("fitx-group9-33b3069bbf0f.json")
    firebase_admin.initialize_app(cred)
except Exception as e:
    print("Firebase Admin SDK Error:", e)

# Check your database or server for scheduled workouts
# For example, if you are using Firebase Firestore
from google.cloud import firestore

db = firestore.Client()

# Replace 'your_collection_name' with the actual name of your collection
# workouts = db.collection('Exercises').stream()
# #users = db.collection("users").document().collections("Workouts")

# # Perform a collection group query for the "Workouts" subcollection

# # Query for workouts under user documents
user_ref = db.collection("users").stream()
for user in user_ref:
    workouts_ref =  db.collection("users").document(user.id).collection("Workouts")
    for workout in workouts_ref.get():
         print("User Uid: "+user.id)
         print("Name of Workout: "+ workout.id)


# query = db.collection_group("Workouts").
# results = []

# for workout_doc in query.stream():
#     # Check if the parent document (user) exists
#     user_ref = workout_doc.reference.parent
#     if user_ref.get().exists:
#         results.append(workout_doc)

# Now, 'results' contains the workout documents that are under user documents.

# Iterate over the result
# users.where()
# for user in users:
    # user_data = user.to_dict()["Workouts"]
    # print(user_data)
    
#.document("AbRoller")
# do = workouts.get()
# print(do.to_dict())
#today = date.today()

#for workout in workouts:
    #workout_data = workout.to_dict()
    #workout_date = workout_data.get('date')

    #if workout_date == today:
        #user_token = workout_data.get('user_token')
        # Send a notification to the user
token = "fSHEUcI5Ri6_iGQPRwWb9g:APA91bGd_zJ5cn1QFZOHqS0a-M_MSuyR3YOF4e-nRqZWl4zAEFhY8FrOY6RWTlyQvZqaY6-3ppRaZzlRZeEV32VT52HhHl8z_7iWB3jxi4urmT6EeEonjqtMT1pdoXmvl7v1NbTIMO9y"  # You should replace this with the actual FCM token of the target device
message = messaging.Message(
    notification=messaging.Notification(
        title="Workout Reminder",
        body="You have a workout scheduled for today!",
    ),
    token=token,
)
response = messaging.send(message)

