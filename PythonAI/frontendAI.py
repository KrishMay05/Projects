from backendAI import get_ai_response as gar
import tkinter as tk


gui = tk.Tk()

conversation = tk.Text(gui, state='disabled')
conversation.pack()

prompt = tk.Label(gui, text="Message Krish's AI...")
prompt.pack()

user_input = tk.Entry(gui)
user_input.pack()

def display_input():
    user_text = user_input.get()
    ai_response = gar(user_text)
    conversation.config(state='normal')
    conversation.insert(tk.END, "User: " + user_text + "\n")
    conversation.insert(tk.END, "AI: " + ai_response + "\n")
    conversation.config(state='disabled')
    user_input.delete(0, tk.END)
def terminate():
    conversation.insert(tk.END, "AI: Thanks for using Krish's AI.\n")
    exit()
    
button = tk.Button(gui, text="Submit", command=display_input)
button.pack()

button2 = tk.Button(gui, text="Terminate", command=terminate)
button2.pack()

gui.mainloop()