import whisper
model = whisper.load_model("base")
result = model.transcribe("video.mp4.mp4")
print(result["text"])



# AQ.Ab8RN6Lwly23CJRnvO86ti8V614e3mFgQrwKGmL1456Cv-0_xQ