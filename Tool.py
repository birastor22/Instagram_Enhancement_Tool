import json
import os

def followers_to_set(followers_path):
    """
    Read followers data from a JSON file and return a set of follower usernames.

    Parameters:
    followers_path (str): Path to the JSON file containing followers data.

    Returns:
    set: A set of follower usernames.
    """
    try:
        with open(followers_path) as f:
            data = json.load(f)
        values = set()
        for item in data:
            value = item['string_list_data'][0]['value']
            values.add(value)
        return values
    except Exception as e:
        print(f"Error reading followers data from '{followers_path}': {e}")

def following_to_list(following_path):
    """
    Read following data from a JSON file and return a list of following usernames.

    Parameters:
    following_path (str): Path to the JSON file containing following data.

    Returns:
    list: A list of following usernames.
    """
    try:
        with open(following_path) as file:
            data = json.load(file)
        values = []
        for item in data['relationships_following']:
            value = item['string_list_data'][0]['value']
            values.append(value)
        return values
    except Exception as e:
        print(f"Error reading following data from '{following_path}': {e}")

def write_to_text(followers, following):
    """
    Write the usernames of users not following back to a text file.

    Parameters:
    followers (set): Set of follower usernames.
    following (list): List of following usernames.
    """
    not_following_back = []
    for user in following:
        if user not in followers:
            not_following_back.append(user)
    file_path = os.path.join(os.path.dirname(__file__), "not_following_back.txt")
    with open(file_path, 'w') as file:
        for item in not_following_back:
            file.write(item + '\n')
    print("Success: Results written to 'not_following_back.txt'")
    print("The file is located at: " + os.path.dirname(os.path.abspath(__file__)))

# User Input
followers_path = input("Input path to followers JSON file: ")
following_path = input("Input path to following JSON file: ")
print()

# Main Execution
try:
    followers_set = followers_to_set(followers_path)
    following_list = following_to_list(following_path)
    write_to_text(followers_set, following_list)
except Exception as e:
    print(f"Failure: {e}")
