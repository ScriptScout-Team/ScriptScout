import google.generativeai as genai

genai.configure(api_key="YOUR_API_KEY_HERE")

model = genai.GenerativeModel("gemini-3.5-flash")

transcript = """

"""

prompt = f"""
Analyze this transcript and return:

1. Summary
2. Genre
3. Tags
4. Keywords
5. Language
6. Age Rating

Transcript:
{transcript}
"""

response = model.generate_content(prompt)

print(response.text)






def analyze_transcript(transcript):

    prompt = f"""
Analyze this transcript and return ONLY valid JSON.

Do not add markdown or explanations.

Use exactly this format:

{{
  "summary": "",
  "genre": "",
  "tags": [],
  "keywords": [],
  "language": "",
  "age_rating": "",
  "timestamps": [
    {{
      "time": "",
      "topic": ""
    }}
  ]
}}

Transcript:
{transcript}
"""

    response = model.generate_content(prompt)

    return {
        "transcript": transcript,
        "metadata": response.text
    }