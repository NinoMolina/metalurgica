VERSION 5.00
Begin VB.Form Hojadistribucion 
   Caption         =   "Hoja De Distribución"
   ClientHeight    =   5370
   ClientLeft      =   120
   ClientTop       =   420
   ClientWidth     =   6900
   LinkTopic       =   "Form1"
   ScaleHeight     =   5370
   ScaleWidth      =   6900
   StartUpPosition =   3  'Windows Default
   Begin VB.Frame Frame1 
      Caption         =   "Distribución"
      Height          =   3495
      Left            =   480
      TabIndex        =   6
      Top             =   1440
      Width           =   5775
   End
   Begin VB.ComboBox Combo1 
      Height          =   315
      Left            =   2640
      TabIndex        =   5
      Text            =   "CAMION II"
      Top             =   960
      Width           =   1335
   End
   Begin VB.Line Line1 
      X1              =   480
      X2              =   6240
      Y1              =   720
      Y2              =   720
   End
   Begin VB.Label Label5 
      Caption         =   "Unidad de Transporte:"
      Height          =   255
      Index           =   1
      Left            =   720
      TabIndex        =   4
      Top             =   960
      Width           =   1695
   End
   Begin VB.Label Label4 
      Caption         =   "27/04/09"
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
      Left            =   4920
      TabIndex        =   3
      Top             =   240
      Width           =   975
   End
   Begin VB.Label Label3 
      Caption         =   "Fecha:"
      Height          =   255
      Left            =   4200
      TabIndex        =   2
      Top             =   240
      Width           =   615
   End
   Begin VB.Label Label2 
      Caption         =   "3"
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
      Left            =   2760
      TabIndex        =   1
      Top             =   240
      Width           =   375
   End
   Begin VB.Label Label1 
      Caption         =   "Nro Hoja de Distribución:"
      Height          =   255
      Left            =   840
      TabIndex        =   0
      Top             =   240
      Width           =   1935
   End
End
Attribute VB_Name = "Hojadistribucion"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
