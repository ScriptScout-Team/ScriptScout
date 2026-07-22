from fastapi import FastAPI, UploadFile, File
import shutil
import whisper
import google.generativeai as genai
import os
from dotenv import load_dotenv

app = FastAPI()

# Whisper Model
whisper_model = whisper.load_model("small")

# Gemini
load_dotenv()
genai.configure(api_key=os.getenv("GEMINI_API_KEY"))
gemini_model = genai.GenerativeModel("gemini-3.5-flash")


def analyze_transcript(transcript):

    prompt = f"""
Analyze this transcript and return EXACTLY in this format.

IMPORTANT:
Do NOT repeat the transcript.
Only return Summary, Genre, Tags, Keywords, Language, Age Rating, and Timestamps.

Summary:
<summary>

Genre:
<genre>

Tags:
- tag1
- tag2
- tag3

Keywords:
- keyword1
- keyword2
- keyword3

Language:
<language>

Age Rating:
<rating>

Timestamps:
00:00 - topic
00:30 - topic
01:00 - topic

Transcript:
{transcript}
"""

    response = gemini_model.generate_content(prompt)

    return {
        "transcript": transcript,
        "metadata": response.text
    }


@app.get("/")
def home():
    return {"message": "GenAI API Working"}


@app.get("/analyze")
def analyze():

    result = whisper_model.transcribe(
        "video.mp4.mp4",
        language="te",
        task="transcribe"
    )

    transcript = result["text"]

    return analyze_transcript(transcript)


@app.post("/upload")
async def upload_video(file: UploadFile = File(...)):

    # Save uploaded video
    with open(file.filename, "wb") as buffer:
        shutil.copyfileobj(file.file, buffer)

    # Whisper transcription (Telugu test)
    result = whisper_model.transcribe(
        file.filename,
        language="te",
        task="transcribe"
    )

    transcript = result["text"]

    return analyze_transcript(transcript)