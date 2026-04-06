import hashlib

# The string to hash

input_string = "hello world"

# Encode the string to bytes

bytes_to_hash = input_string.encode('utf-8')

# Create a SHA-256 hash object

hash_object = hashlib.sha256(bytes_to_hash)

# Get the hexadecimal representation of the hash

hex_hash = hash_object.hexdigest()

# Print the result

print(f"Original string: {input_string}")

print(f"SHA-256 hash: {hex_hash}")