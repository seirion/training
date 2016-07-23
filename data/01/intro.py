#-*- coding: utf-8 -*-

users = [
    { "id": 0, "name": "Hero" },
    { "id": 1, "name": "Dunn" },
    { "id": 2, "name": "Sue" },
    { "id": 3, "name": "Chi" },
    { "id": 4, "name": "Thor" },
    { "id": 5, "name": "Clive" },
    { "id": 6, "name": "Hicks" },
    { "id": 7, "name": "Devin" },
    { "id": 8, "name": "Kate" },
    { "id": 9, "name": "Klein" },
    { "id": 10, "name": "Jen" }
]

friendships = [(0, 1), (0, 2), (1, 2), (1, 3), (2, 3), (3, 4),
        (4, 5), (5, 6), (5, 7), (6, 8), (7, 8), (8, 9)]

for user in users:
    user["friends"] = []

# 친구 구하기
for i, j in friendships:
    users[i]["friends"].append(users[j])
    users[j]["friends"].append(users[i])

# id 와 친구 수 출력하기
#for user in users:
#    print user["name"], len(user["friends"])

total_num = sum(len(user["friends"]) for user in users)

print "total friends : ", total_num
print "average : ", total_num / len(users)

def num_of_friend(user):
    return len(user["friends"])

num = sum(num_of_friend(user) for user in users)
print "total friends : ", num

# list of (name, 친구 수)
name_friend_num = [(user["name"], len(user["friends"])) for user in users]

print name_friend_num

#sorted(name_friend_num, key=lambda (name, num): num)
sorted_by_friend_num = sorted(name_friend_num, key=lambda a: a[1], reverse=True)

print sorted_by_friend_num 


####


