from fastapi import FastAPI
import whisper
  
app=FastAPI()
model = whisper.load_model("base")

@app.get("/")
def home():
    return {"message":"gen api working"} 

@app.get("/transcribe")
def transcribe():
    result = model.transcribe("video.mp4.mp4")
    return {"transcript": result["text"]}
     