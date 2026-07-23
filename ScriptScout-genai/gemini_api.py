import google.generativeai as genai

genai.configure(api_key="AQ.Ab8RN6Lwly23CJRnvO86ti8V614e3mFgQrwKGmL1456Cv-0_xQ")

model = genai.GenerativeModel("gemini-3.5-flash")

response = model.generate_content("Say Hello")

print(response.text)