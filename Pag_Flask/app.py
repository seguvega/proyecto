from flask import Flask, render_template, request

app = Flask(__name__)# Crea un proyecto

@app.route("/")
def inicio():
    return render_template('index.html', titulo=' ')

@app.route("/info")
def infor():
    return render_template('informacion.html', titulo=' I N F O R M A C I O N')


app.run(debug=True)
