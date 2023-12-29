from AIBrain import get_ai_response as gar
import tkinter as tk


gui = tk.Tk()

conversation = tk.Text(gui, state='disabled')
conversation.pack()

prompt = tk.Label(gui, text="Enter the name of the file you want corrected. (Ex: grammer.txt)")
prompt.pack()

user_input = tk.Entry(gui)
user_input.pack()

def display_input():
    user_text = user_input.get()
    if("." in user_text):
        try:
            print(user_text)
            open("fixedgrammer.txt", 'w').close()
            file = open(user_text, "r")
            file2 = open("fixedgrammer.txt", "w")
            print("Execute")
            contents = file.read()
            ai_response = gar(contents)
            file2.write(ai_response)
            conversation.config(state='normal')
            conversation.insert(tk.END, "AI: " + ai_response + "\n")
            conversation.config(state='disabled')
            user_input.delete(0, tk.END)
            file2.close()
            file.close()
        except:
            conversation.config(state='normal')
            conversation.insert(tk.END, "AI: File not found.\n")
            conversation.config(state='disabled')
            user_input.delete(0, tk.END)
            return
        pass
    else:
        conversation.config(state='normal')
        conversation.insert(tk.END, "AI: File not found.\n")
        conversation.config(state='disabled')
        user_input.delete(0, tk.END)
        pass
        
    # ai_response = gar(user_text)
    # conversation.config(state='normal')
    # conversation.insert(tk.END, "User: " + user_text + "\n")
    # conversation.insert(tk.END, "AI: " + ai_response + "\n")
    # conversation.config(state='disabled')
    # user_input.delete(0, tk.END)
def terminate():
    conversation.insert(tk.END, "AI: Thanks for using Krish's AI.\n")
    exit()
    
button = tk.Button(gui, text="Submit", command=display_input)
button.pack()

button2 = tk.Button(gui, text="Terminate", command=terminate)
button2.pack()

gui.mainloop()