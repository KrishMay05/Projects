import tkinter as tk
import openai

openai.api_key = 'Your custom AI API key'
def get_ai_response(message):
    response = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role": "system", "content": "You are a helpful assistant." +
                "You will impersonate certain celebrities when given instructions regarding specific fields." +
                "You will impersonate Gordon Ramsay for any food related message, " +
                "Arnold Schwarzenegger for any fitness related message, " +
                "Bruno Mars for any music related message, " +
                "Quentin Tarantino for any film related message, " +
                "and Morgan Freeman for any other type of message request." +
                "Keep your responses as breif as possible for clarity and ease of reading" +
                "If the user asks for anything illegal according to the U.S. Constitution "+
                "return the string 'I am unable to tell you any illegal or shady request due to restrictions placed by Krish Patel.'"},
            # ^ bounds for the AI chat bot, "system" refers to the AI chatbot, content and everything after is the customization
            {"role": "user", "content": message}
            # ^ What the AI chatbot takes as an input, message is passed in function call from the user.
        ],
        temprature = .55, 
        # Influences the randomness of the generated responses, a higher value such as 0.8 makes the answers more diverse
        top_p = .5,
        # It limits the cumulative probability of the most likely tokens; Higher values like 0.9 allow more tokens, leading to diverse responses
        frequency_penalty=0.7,
        # frequency_penalty parameter allows you to control the model's tendency to generate repetitive responses - higher == more diverse.
    )
    return response['choices'][0]['message']['content']
    
# gui = tk.Tk()

# # Create a Text widget to display the conversation.
# conversation = tk.Text(gui, state='disabled')
# conversation.pack()

# prompt = tk.Label(gui, text="Message Krish's AI...")
# prompt.pack()

# user_input = tk.Entry(gui)
# user_input.pack()

# def display_input():
#     # Get the user's input and the AI's response.
#     user_text = user_input.get()
#     ai_response = get_ai_response(user_text)

#     # Temporarily set the state to normal so we can insert text.
#     conversation.config(state='normal')

#     # Append the user's input and the AI's response to the conversation Text widget.
#     conversation.insert(tk.END, "User: " + user_text + "\n")
#     conversation.insert(tk.END, "AI: " + ai_response + "\n")

#     # Set the state back to disabled so the user can't edit the text.
#     conversation.config(state='disabled')

#     # Clear the user input field.
#     user_input.delete(0, tk.END)
# def terminate():
#     conversation.insert(tk.END, "AI: Thanks for using Krish's AI.\n")
#     exit()
# button = tk.Button(gui, text="Submit", command=display_input)
# button.pack()

# button2 = tk.Button(gui, text="Terminate", command=terminate)
# button2.pack()

# gui.mainloop()
