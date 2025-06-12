import tkinter as tk
import json

class LogicCircuitDrawer:
    def __init__(self, master, functions_data):
        """
        Inicializa el dibujador de circuitos lógicos.

        Args:
            master: La ventana principal de Tkinter.
            functions_data: Una lista de diccionarios que describen las funciones y sus circuitos.
        """
        self.master = master
        master.title("Dibujador de Circuitos Lógicos")

        self.canvas = tk.Canvas(master, width=800, height=600, bg="white", bd=2, relief="groove")
        self.canvas.pack(pady=20)

        # Crear un Frame para los botones de selección de circuito
        button_frame = tk.Frame(master)
        button_frame.pack(pady=10)

        # Usar la lista de funciones actualizada
        self.functions_data = functions_data

        # Crear botones para cada circuito
        for i, func in enumerate(self.functions_data):
            btn_text = f"Circuito {i+1}: {func['name']}"
            tk.Button(button_frame, text=btn_text, command=lambda f=func: self.draw_selected_circuit(f)).pack(side=tk.LEFT, padx=5)

        # Dibujar el Circuito 3 por defecto al iniciar
        if self.functions_data:
            self.draw_selected_circuit(self.functions_data[2])

    def draw_selected_circuit(self, func_data):
        """
        Borra el canvas y dibuja el circuito seleccionado.

        Args:
            func_data: El diccionario que contiene la información del circuito a dibujar.
        """
        self.canvas.delete("all") # Borrar todo lo que hay en el canvas
        self.current_circuit_data = func_data['circuit']
        self.draw_circuit()
        self.master.title(f"Dibujador de Circuitos Lógicos - {func_data['name']}")

    def draw_circuit(self):
        """
        Dibuja el circuito lógico actual en el canvas.
        Las posiciones de las compuertas están predefinidas para cada circuito
        para una visualización básica.
        """
        circuit = self.current_circuit_data
        gates = circuit['gates']
        inputs = circuit['inputs']

        # Diccionario para almacenar las coordenadas de salida de cada compuerta/entrada
        # Esto es crucial para conectar las líneas
        output_coords = {}

        # Coordenadas iniciales para las entradas
        input_y_start = 100 # Empezar un poco más abajo para este circuito
        input_x = 50
        for i, input_name in enumerate(inputs):
            y = input_y_start + i * 100 # Mayor espaciado para las entradas
            self.canvas.create_text(input_x - 10, y, text=input_name, anchor="e", font=("Arial", 12, "bold"))
            output_coords[input_name] = (input_x, y) # Punto de salida de la entrada

        # Posiciones predefinidas para cada compuerta por circuito (ajustar según sea necesario)
        gate_positions = {}
        if circuit['output'] == 'f(A,B)': # Circuito 1: (A + B)(B̄ + C)
            gate_positions = {
                'OR1': (200, 75),  # OR1 (A, B)
                'NOT1': (150, 150), # NOT1 (B)
                'OR2': (300, 175), # OR2 (B_bar, C)
                'AND1': (450, 125) # AND1 (X1, X2)
            }
        elif circuit['output'] == 'f(A,B,C)': # Circuito 2: A(B̄ + C)
            gate_positions = {
                'NOT1': (150, 150), # NOT1 (B)
                'OR1': (250, 175),  # OR1 (B_bar, C)
                'AND1': (400, 125)  # AND1 (A, X1)
            }
        elif circuit['output'] == 'f(A,B,C,D)': # Circuito 3: AD + (Ā + B + C)̄
            gate_positions = {
                'AND1': (200, 75),   # AND1 (A, D)
                'NOT_A': (150, 200), # NOT_A (A)
                'OR_ABC': (300, 250), # OR_ABC (A_bar, B, C)
                'NOT_OR_ABC': (450, 250), # NOT_OR_ABC (X2)
                'OR_FINAL': (600, 175) # OR_FINAL (X1, X3)
            }
        elif circuit['output'] == 'f(A,B,C) = (Ā · C̄) + (Ā · B · C̄) + (A · B)': # Circuito 4
            gate_positions = {
                'NOT_A': (150, 100),
                'NOT_C': (150, 300),
                'AND_T1': (300, 175),    # (A_bar . C_bar)
                'AND_T2a': (300, 275),   # (A_bar . B)
                'AND_T2b': (450, 275),   # (T2a_out . C_bar)
                'AND_T3': (300, 400),    # (A . B)
                'OR_Partial': (600, 225), # (T1_out + T2_out)
                'OR_Final': (700, 337.5) # (OR_Partial_out + T3_out) - Ajustado para centrar mejor
            }


        # Dibujar compuertas y sus conexiones
        for gate in gates:
            gate_id = gate['id']
            gate_type = gate['type']
            gate_output = gate['output']
            
            # Obtener la posición de la compuerta
            x, y = gate_positions.get(gate_id, (100, 100)) # Posición por defecto si no está definida

            # Dibujar la compuerta y obtener las coordenadas de sus entradas y salida
            # Pasar el número de entradas de la compuerta para que draw_gate las genere dinámicamente
            input_coords_list, output_coord = self.draw_gate(x, y, gate_type, gate_id, len(gate['inputs']))
            output_coords[gate_output] = output_coord # Guardar la coordenada de salida de esta compuerta

            # Dibujar las conexiones de entrada a la compuerta
            for i, input_name in enumerate(gate['inputs']):
                if input_name in output_coords:
                    start_x, start_y = output_coords[input_name]
                    end_x, end_y = input_coords_list[i]

                    # Enrutamiento de líneas mejorado para un aspecto más "prolijo" (codos en L)
                    # Ajustes para rutas específicas de este circuito complejo
                    if circuit['output'] == 'f(A,B,C) = (Ā · C̄) + (Ā · B · C̄) + (A · B)': # Rutas para Circuito 4
                        if gate_id == 'AND_T1': # A_bar y C_bar a AND_T1
                            if input_name == 'A_bar':
                                self.canvas.create_line(start_x, start_y, start_x + 50, start_y, fill="blue", width=2)
                                self.canvas.create_line(start_x + 50, start_y, start_x + 50, end_y, fill="blue", width=2)
                                self.canvas.create_line(start_x + 50, end_y, end_x, end_y, fill="blue", width=2)
                            elif input_name == 'C_bar':
                                self.canvas.create_line(start_x, start_y, start_x + 50, start_y, fill="blue", width=2)
                                self.canvas.create_line(start_x + 50, start_y, start_x + 50, end_y, fill="blue", width=2)
                                self.canvas.create_line(start_x + 50, end_y, end_x, end_y, fill="blue", width=2)
                        elif gate_id == 'AND_T2a': # A_bar y B a AND_T2a
                             if input_name == 'A_bar':
                                self.canvas.create_line(start_x, start_y, start_x + 30, start_y, fill="blue", width=2)
                                self.canvas.create_line(start_x + 30, start_y, start_x + 30, end_y, fill="blue", width=2)
                                self.canvas.create_line(start_x + 30, end_y, end_x, end_y, fill="blue", width=2)
                             else: # B
                                self.canvas.create_line(start_x, start_y, end_x, start_y, fill="blue", width=2)
                                self.canvas.create_line(end_x, start_y, end_x, end_y, fill="blue", width=2)
                        elif gate_id == 'AND_T3': # A y B a AND_T3
                             self.canvas.create_line(start_x, start_y, end_x, start_y, fill="blue", width=2)
                             self.canvas.create_line(end_x, start_y, end_x, end_y, fill="blue", width=2)
                        elif gate_id == 'AND_T2b' and input_name == 'C_bar': # C_bar a AND_T2b
                            self.canvas.create_line(start_x, start_y, start_x + 50, start_y, fill="blue", width=2)
                            self.canvas.create_line(start_x + 50, start_y, start_x + 50, end_y, fill="blue", width=2)
                            self.canvas.create_line(start_x + 50, end_y, end_x, end_y, fill="blue", width=2)
                        elif gate_id == 'OR_Partial': # T1_out a OR_Partial
                            if input_name == 'T1_out':
                                self.canvas.create_line(start_x, start_y, start_x + 100, start_y, fill="blue", width=2)
                                self.canvas.create_line(start_x + 100, start_y, start_x + 100, end_y, fill="blue", width=2)
                                self.canvas.create_line(start_x + 100, end_y, end_x, end_y, fill="blue", width=2)
                            else: # T2_out
                                self.canvas.create_line(start_x, start_y, start_x + 50, start_y, fill="blue", width=2)
                                self.canvas.create_line(start_x + 50, start_y, start_x + 50, end_y, fill="blue", width=2)
                                self.canvas.create_line(start_x + 50, end_y, end_x, end_y, fill="blue", width=2)
                        elif gate_id == 'OR_Final' and input_name == 'T3_out': # T3_out a OR_Final
                            self.canvas.create_line(start_x, start_y, start_x + 100, start_y, fill="blue", width=2)
                            self.canvas.create_line(start_x + 100, start_y, start_x + 100, end_y, fill="blue", width=2)
                            self.canvas.create_line(start_x + 100, end_y, end_x, end_y, fill="blue", width=2)
                        else: # Enrutamiento por defecto L-bend
                            mid_x_bend = start_x + 30
                            if mid_x_bend > end_x - 10:
                                mid_x_bend = (start_x + end_x) / 2
                            self.canvas.create_line(start_x, start_y, mid_x_bend, start_y, fill="blue", width=2)
                            self.canvas.create_line(mid_x_bend, start_y, mid_x_bend, end_y, fill="blue", width=2)
                            self.canvas.create_line(mid_x_bend, end_y, end_x, end_y, fill="blue", width=2)
                    else: # Enrutamiento por defecto L-bend para otros circuitos
                        mid_x_bend = start_x + 30
                        if mid_x_bend > end_x - 10:
                            mid_x_bend = (start_x + end_x) / 2
                        self.canvas.create_line(start_x, start_y, mid_x_bend, start_y, fill="blue", width=2)
                        self.canvas.create_line(mid_x_bend, start_y, mid_x_bend, end_y, fill="blue", width=2)
                        self.canvas.create_line(mid_x_bend, end_y, end_x, end_y, fill="blue", width=2)
                else:
                    print(f"Advertencia: No se encontraron coordenadas de salida para la entrada {input_name} de la compuerta {gate_id}.")


        # Dibujar la línea de salida final del circuito
        final_output_name = circuit['output']
        if final_output_name in output_coords:
            start_x, start_y = output_coords[final_output_name]
            self.canvas.create_line(start_x, start_y, start_x + 50, start_y, fill="blue", width=2)
            self.canvas.create_text(start_x + 60, start_y, text="f(Output)", anchor="w", font=("Arial", 12, "bold")) # Texto genérico para salida
            # Puedes poner el nombre completo de la función si quieres:
            # self.canvas.create_text(start_x + 60, start_y + 20, text=final_output_name, anchor="w", font=("Arial", 10), fill="gray")


    def draw_gate(self, x_center, y_center, gate_type, gate_id, num_inputs):
        """
        Dibuja una compuerta lógica en el canvas y devuelve las coordenadas de sus entradas y salida.

        Args:
            x_center: Coordenada X del centro de la compuerta.
            y_center: Coordenada Y del centro de la compuerta.
            gate_type: Tipo de compuerta ('AND', 'OR', 'NOT').
            gate_id: ID de la compuerta (para etiquetado).
            num_inputs: El número esperado de entradas para esta compuerta.

        Returns:
            Una tupla: (lista de coordenadas de entrada, coordenada de salida).
        """
        width = 80
        height = 60
        x1, y1 = x_center - width / 2, y_center - height / 2
        x2, y2 = x_center + width / 2, y_center + height / 2

        input_coords = []
        output_coord = (x2, y_center) # Salida por defecto a la derecha

        # Dibujar el cuerpo de la compuerta con formas más específicas
        if gate_type == 'AND':
            # Forma de D para compuerta AND
            self.canvas.create_line(x1, y1, x1, y2, fill="black", width=2) # Línea vertical izquierda
            self.canvas.create_line(x1, y1, x_center, y1, fill="black", width=2) # Línea horizontal superior hasta el inicio de la curva
            self.canvas.create_line(x1, y2, x_center, y2, fill="black", width=2) # Línea horizontal inferior hasta el inicio de la curva
            # Arco derecho (forma de D)
            self.canvas.create_arc(x_center - height/2, y1, x2, y2, start=270, extent=180, style=tk.ARC, outline="black", width=2)
            # Relleno (aproximado con un polígono)
            self.canvas.create_polygon(x1, y1, x_center, y1, x2, y_center, x_center, y2, x1, y2, fill="lightblue", outline="")

            # Entradas para AND (distribuidas dinámicamente)
            if num_inputs == 1: # Aunque AND/OR usualmente tienen 2+, se maneja por si acaso.
                input_coords.append((x1, y_center))
            else:
                # Distribuir entradas uniformemente
                for i in range(num_inputs):
                    input_y = y1 + (height / (num_inputs + 1)) * (i + 1)
                    input_coords.append((x1, input_y))

        elif gate_type == 'OR':
            # Compuerta OR (entrada curvada, salida puntiaguda)
            # Aproximando con un polígono para el cuerpo principal y arcos para las curvas
            points = [
                x1 + 10, y1,  # Inicio de curva superior izquierda
                x2 - 10, y1,  # Superior derecha antes de curva de salida
                x2, y_center, # Punto de salida
                x2 - 10, y2,  # Inferior derecha antes de curva de salida
                x1 + 10, y2   # Inicio de curva inferior izquierda
            ]
            self.canvas.create_polygon(points, fill="lightblue", outline="black", width=2)

            # Añadir la curva de entrada (lado izquierdo)
            self.canvas.create_arc(x1 - 10, y1, x1 + 30, y2, start=270, extent=180, style=tk.ARC, outline="black", width=2)
            
            # Entradas para OR (distribuidas dinámicamente)
            if num_inputs == 1:
                input_coords.append((x1 + 10, y_center))
            else:
                for i in range(num_inputs):
                    input_y = y1 + (height / (num_inputs + 1)) * (i + 1)
                    input_coords.append((x1 + 10, input_y)) # Ajustado para entrada curvada

        elif gate_type == 'NOT':
            # Triángulo para compuerta NOT
            points = [x1, y1, x2, y_center, x1, y2]
            self.canvas.create_polygon(points, outline="black", fill="lightblue", width=2)
            
            # Círculo de negación
            self.canvas.create_oval(x2, y_center - 5, x2 + 10, y_center + 5, outline="black", fill="black")
            output_coord = (x2 + 10, y_center) # Ajustar salida por el círculo

            # Entrada para NOT (siempre una)
            input_coords.append((x1, y_center))

        # Dibujar etiqueta de texto para el tipo de compuerta
        self.canvas.create_text(x_center, y_center, text=gate_type, font=("Arial", 12, "bold"))
        self.canvas.create_text(x_center, y_center - height/2 - 10, text=gate_id, font=("Arial", 8), fill="gray")

        # Dibujar puntos de conexión de entrada/salida (pequeños círculos)
        for ix, iy in input_coords:
            self.canvas.create_oval(ix - 3, iy - 3, ix + 3, iy + 3, fill="red", outline="red")
        self.canvas.create_oval(output_coord[0] - 3, output_coord[1] - 3, output_coord[0] + 3, output_coord[1] + 3, fill="green", outline="green")

        return input_coords, output_coord

# Datos de las funciones (copiados y extendidos)
functions_data = [
    {
        "name": "f(A,B) = (A + B)(B̄ + C)",
        "description": "Este circuito combina dos operaciones OR con una AND. La entrada B se niega en una de las ramas.",
        "circuit": {
            "inputs": ['A', 'B', 'C'],
            "gates": [
                {"id": 'OR1', "type": 'OR', "inputs": ['A', 'B'], "output": 'X1'},
                {"id": 'NOT1', "type": 'NOT', "inputs": ['B'], "output": 'B_bar'},
                {"id": 'OR2', "type": 'OR', "inputs": ['B_bar', 'C'], "output": 'X2'},
                {"id": 'AND1', "type": 'AND', "inputs": ['X1', 'X2'], "output": 'f(A,B)'},
            ],
            "output": 'f(A,B)',
        },
    },
    {
        "name": "f(A,B,C) = A(B̄ + C)",
        "description": "Este circuito usa una negación, una OR y una AND para su implementación.",
        "circuit": {
            "inputs": ['A', 'B', 'C'],
            "gates": [
                {"id": 'NOT1', "type": 'NOT', "inputs": ['B'], "output": 'B_bar'},
                {"id": 'OR1', "type": 'OR', "inputs": ['B_bar', 'C'], "output": 'X1'},
                {"id": 'AND1', "type": 'AND', "inputs": ['A', 'X1'], "output": 'f(A,B,C)'},
            ],
            "output": 'f(A,B,C)',
        },
    },
    {
        "name": "f(A,B,C,D) = AD + (Ā + B + C)̄",
        "description": "Un circuito más complejo que incluye una negación de una operación OR de tres entradas, combinada con una AND.",
        "circuit": {
            "inputs": ['A', 'B', 'C', 'D'],
            "gates": [
                {"id": 'AND1', "type": 'AND', "inputs": ['A', 'D'], "output": 'X1'},
                {"id": 'NOT_A', "type": 'NOT', "inputs": ['A'], "output": 'A_bar'},
                {"id": 'OR_ABC', "type": 'OR', "inputs": ['A_bar', 'B', 'C'], "output": 'X2'},
                {"id": 'NOT_OR_ABC', "type": 'NOT', "inputs": ['X2'], "output": 'X3'},
                {"id": 'OR_FINAL', "type": 'OR', "inputs": ['X1', 'X3'], "output": 'f(A,B,C,D)'},
            ],
            "output": 'f(A,B,C,D)',
        },
    },
    {
        "name": "f(A,B,C) = (Ā · C̄) + (Ā · B · C̄) + (A · B)",
        "description": "Circuito que implementa una suma de productos, con términos negados y de tres entradas.",
        "circuit": {
            "inputs": ['A', 'B', 'C'],
            "gates": [
                {"id": 'NOT_A', "type": 'NOT', "inputs": ['A'], "output": 'A_bar'},
                {"id": 'NOT_C', "type": 'NOT', "inputs": ['C'], "output": 'C_bar'},
                {"id": 'AND_T1', "type": 'AND', "inputs": ['A_bar', 'C_bar'], "output": 'T1_out'},
                {"id": 'AND_T2a', "type": 'AND', "inputs": ['A_bar', 'B'], "output": 'T2a_out'},
                {"id": 'AND_T2b', "type": 'AND', "inputs": ['T2a_out', 'C_bar'], "output": 'T2_out'},
                {"id": 'AND_T3', "type": 'AND', "inputs": ['A', 'B'], "output": 'T3_out'},
                {"id": 'OR_Partial', "type": 'OR', "inputs": ['T1_out', 'T2_out'], "output": 'OR_Partial_out'},
                {"id": 'OR_Final', "type": 'OR', "inputs": ['OR_Partial_out', 'T3_out'], "output": 'f(A,B,C) = (Ā · C̄) + (Ā · B · C̄) + (A · B)'},
            ],
            "output": 'f(A,B,C) = (Ā · C̄) + (Ā · B · C̄) + (A · B)',
        },
    },
]

if __name__ == "__main__":
    root = tk.Tk()
    app = LogicCircuitDrawer(root, functions_data)
    root.mainloop()