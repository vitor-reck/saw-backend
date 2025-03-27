db.users.insertMany([
    {
        "_id": "827b1a40-0ff6-4eec-9ceb-16e589fb0ed8",
        "name": "John Doe",
        "email": "john@gmail.com",
        "password": "$2a$12$m4K8N4Tl7I9cw22BB3spou7zWZN4f7dlxgRPbYNnIH5G5uYE0Moz.",
        "role": "admin",
        "is_active": true,
        "created_at": new Date("2022-09-27 18:00:00.000"),
        "updated_at": new Date("2022-09-27 18:00:00.000")
    },
    {
        "_id": "727b1a40-0ff6-4eec-9ceb-16e589fb0ed8",
        "name": "Jane Doe",
        "email": "jane@gmail.com",
        "password": "$2a$12$vwEX/r9EaPHMIEBUaPkNcOqRGJe..jr0qrGZL93ozQheD8xzPjc/G",
        "role": "user",
        "is_active": true,
        "created_at": new Date("2022-09-27 18:00:00.000"),
        "updated_at": new Date("2022-09-27 18:00:00.000")
    }
])

db.categories.insertMany([
    {
        "_id": "927b1a40-0ff6-4eec-9ceb-16e589fb0ed8",
        "name": "Higiene",
        "created_at": new Date("2022-09-27 18:00:00.000"),
        "updated_at": new Date("2022-09-27 18:00:00.000")
    },
    {
        "_id": "627b1a40-0ff6-4eec-9ceb-16e589fb0ed8",
        "name": "Eletrônicos",
        "created_at": new Date("2022-09-27 18:00:00.000"),
        "updated_at": new Date("2022-09-27 18:00:00.000")
    },
    {
        "_id": "527b1a40-0ff6-4eec-9ceb-16e589fb0ed8",
        "name": "Frios",
        "created_at": new Date("2022-09-27 18:00:00.000"),
        "updated_at": new Date("2022-09-27 18:00:00.000")
    }
])