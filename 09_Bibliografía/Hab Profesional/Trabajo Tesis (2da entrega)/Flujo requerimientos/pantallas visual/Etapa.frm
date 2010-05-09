VERSION 5.00
Begin VB.Form Etapa 
   Caption         =   "Nueva Etapa de Producción"
   ClientHeight    =   5520
   ClientLeft      =   120
   ClientTop       =   420
   ClientWidth     =   7830
   LinkTopic       =   "Form1"
   ScaleHeight     =   5520
   ScaleWidth      =   7830
   StartUpPosition =   3  'Windows Default
   Begin VB.CommandButton Command2 
      Caption         =   "Cancelar"
      Height          =   375
      Left            =   3840
      TabIndex        =   21
      Top             =   4680
      Width           =   1095
   End
   Begin VB.CommandButton Command1 
      Caption         =   "Aceptar"
      Height          =   375
      Left            =   5760
      TabIndex        =   20
      Top             =   4680
      Width           =   1095
   End
   Begin VB.TextBox Text4 
      Height          =   285
      Left            =   2760
      TabIndex        =   19
      Text            =   "3"
      Top             =   3960
      Width           =   975
   End
   Begin VB.TextBox Text3 
      Height          =   285
      Index           =   1
      Left            =   6000
      TabIndex        =   17
      Text            =   "1"
      Top             =   3480
      Width           =   615
   End
   Begin VB.TextBox Text3 
      Height          =   285
      Index           =   0
      Left            =   2760
      TabIndex        =   15
      Text            =   "2"
      Top             =   3480
      Width           =   615
   End
   Begin VB.ListBox List1 
      Height          =   840
      ItemData        =   "Etapa.frx":0000
      Left            =   5040
      List            =   "Etapa.frx":0007
      TabIndex        =   12
      Top             =   2280
      Width           =   1695
   End
   Begin VB.ComboBox Combo2 
      Height          =   315
      Left            =   1200
      TabIndex        =   11
      Text            =   "Desplumero"
      Top             =   2280
      Width           =   1335
   End
   Begin VB.TextBox Text2 
      Height          =   285
      Left            =   5400
      TabIndex        =   9
      Text            =   " 100"
      Top             =   1440
      Width           =   1335
   End
   Begin VB.ComboBox Combo1 
      Height          =   315
      Left            =   1800
      TabIndex        =   7
      Text            =   "Entero"
      Top             =   1440
      Width           =   1455
   End
   Begin VB.TextBox Text1 
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   8.25
         Charset         =   0
         Weight          =   400
         Underline       =   0   'False
         Italic          =   -1  'True
         Strikethrough   =   0   'False
      EndProperty
      Height          =   285
      Left            =   1800
      TabIndex        =   4
      Text            =   "desplume"
      Top             =   840
      Width           =   1215
   End
   Begin VB.Label Label12 
      Caption         =   "Duración Estipulada: "
      Height          =   255
      Left            =   480
      TabIndex        =   18
      Top             =   3960
      Width           =   1575
   End
   Begin VB.Label Label11 
      Caption         =   "Cantidad de Horas Hombre:"
      Height          =   255
      Left            =   3840
      TabIndex        =   16
      Top             =   3480
      Width           =   2055
   End
   Begin VB.Label Label10 
      Caption         =   "Cantidad de Horas Máquinas:"
      Height          =   255
      Left            =   480
      TabIndex        =   14
      Top             =   3480
      Width           =   2175
   End
   Begin VB.Line Line2 
      X1              =   360
      X2              =   7440
      Y1              =   1920
      Y2              =   1920
   End
   Begin VB.Label Label9 
      Caption         =   "Lista Máquinas:"
      Height          =   255
      Left            =   3720
      TabIndex        =   13
      Top             =   2280
      Width           =   1215
   End
   Begin VB.Label Label8 
      Caption         =   "Máquina:"
      Height          =   255
      Left            =   360
      TabIndex        =   10
      Top             =   2280
      Width           =   855
   End
   Begin VB.Label Label7 
      Caption         =   "Cantidad Base:"
      Height          =   255
      Left            =   4200
      TabIndex        =   8
      Top             =   1440
      Width           =   1215
   End
   Begin VB.Line Line1 
      X1              =   360
      X2              =   7440
      Y1              =   600
      Y2              =   600
   End
   Begin VB.Label Label6 
      Caption         =   "Tipo de Producto:"
      Height          =   255
      Left            =   360
      TabIndex        =   6
      Top             =   1440
      Width           =   1335
   End
   Begin VB.Label Label5 
      Caption         =   "Nombre:"
      Height          =   255
      Left            =   960
      TabIndex        =   5
      Top             =   840
      Width           =   735
   End
   Begin VB.Label Label4 
      Caption         =   "25/08/07"
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   8.25
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   255
      Left            =   5880
      TabIndex        =   3
      Top             =   240
      Width           =   975
   End
   Begin VB.Label Label3 
      Caption         =   "Fecha Creación:"
      Height          =   255
      Left            =   4560
      TabIndex        =   2
      Top             =   240
      Width           =   1215
   End
   Begin VB.Label Label2 
      Caption         =   "4"
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   8.25
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   255
      Left            =   2280
      TabIndex        =   1
      Top             =   240
      Width           =   375
   End
   Begin VB.Label Label1 
      Caption         =   "Nro Etapa:"
      Height          =   255
      Left            =   1320
      TabIndex        =   0
      Top             =   240
      Width           =   855
   End
End
Attribute VB_Name = "Etapa"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
