Mongo DB details:
====================================================================================================
Connection String:

mongodb+srv://abhinav:<password>@cluster0-ee8bf.mongodb.net/test?retryWrites=true

====================================================================================================
Full Driver example:

MongoClientURI uri = new MongoClientURI(
    "mongodb+srv://abhinav:<password>@cluster0-ee8bf.mongodb.net/test?retryWrites=true");

MongoClient mongoClient = new MongoClient(uri);
MongoDatabase database = mongoClient.getDatabase("test");
