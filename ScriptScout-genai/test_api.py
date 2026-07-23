import os
import requests
import json

test_filename = "speech_example.wav"
speech_url = "https://github.com/Uberi/speech_recognition/raw/master/examples/english.wav"

# 1. Download a small valid WAV file containing actual speech for testing
if not os.path.exists(test_filename):
    print(f"Downloading sample speech WAV file from: {speech_url}...")
    try:
        response = requests.get(speech_url, timeout=30)
        if response.status_code == 200:
            with open(test_filename, "wb") as f:
                f.write(response.content)
            print("Download complete.")
        else:
            print(f"Failed to download speech file: HTTP {response.status_code}")
    except Exception as e:
        print(f"Failed to download speech file: {e}")

# If download failed, fall back to generating a silent WAV file
if not os.path.exists(test_filename):
    import wave
    import struct
    print("Falling back to generating silent WAV file...")
    sample_rate = 16000
    duration = 1.0
    with wave.open(test_filename, "wb") as wav_file:
        wav_file.setnchannels(1)
        wav_file.setsampwidth(2)
        wav_file.setframerate(sample_rate)
        for _ in range(int(sample_rate * duration)):
            wav_file.writeframes(struct.pack("<h", 0))

# 2. Upload file to the live serveo tunnel
api_url = "https://1e1a90661b718c.lhr.life/upload"
print(f"Uploading file to: {api_url}")

try:
    with open(test_filename, "rb") as f:
        files = {"file": (test_filename, f, "audio/wav")}
        # Disable SSL warning verification for local tunnel testing
        response = requests.post(api_url, files=files, timeout=60, verify=False)
        
    print(f"HTTP Status Code: {response.status_code}")
    if response.status_code == 200:
        print("Response received successfully:")
        print(json.dumps(response.json(), indent=2))
    else:
        print(f"API Error Response: {response.text}")
except Exception as e:
    print(f"Request failed: {e}")
