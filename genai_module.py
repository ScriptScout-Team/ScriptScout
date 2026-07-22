import whisper
model = whisper.load_model("base")
result = model.transcribe("video.mp4.mp4")
transcript=result["text"]
print("TRANSCRIPT:")
print(transcript)