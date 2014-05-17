-- [[ Copyright © Simon Cahill alias Beatsleigher ]] -- 
-- [[ Debug script for Universal Android Toolkit. Use with caution. ]] --
-- [[ Version 1.0, date: 15, 05, 2014 (itl.) ]] --

-- [[ Handlers (I hate this top-to-bottom crap that Lua uses (-_-") ]] --
local function runUAT()
  os.execute("java -jar ../Universal_Android_Toolkit.jar")
end

local function runJVVM()
  os.execute("visualvm") -- Change to .exe for Windows-version... (Or, maybe I should tell Windows-users to suck it and leave me be... Hmm. Decisions, decisions... --
end

local function runJDT()
  os.execute("java -jar jdebuggertool.jar")
end

-- [[ Variables ]] --
local VERSION = "1.0"
local TITLE = "Universal Android Toolkit Debugging Script Ver.:"..VERSION
local MENU = {
  [1] = { text = "Run Universal Android Toolkit", handler = runUAT },
  [2] = { text = "Run Java Visual VM", handler = runJVVM },
  [3] = { text = "Run JDebugTool", handler = runJDT }
}
local SELECTED_ITEM = 1

-- [[ Printing Methods (Don't touch it, or it'll explode!) ]] -- 

local function printCentre(text)
  print("\t\t" .. text)
end

local function printHeader()
  os.execute("clear")
  printCentre("========== " .. TITLE .. " ==========")
  printCentre(" Universal Android Toolkit Debugger. Please select an option from below:")
end

local function printMenu() 
  POSITION = 1
  
  for i = 0, #MENU do 
    if i == SELECTED_ITEM then
      printCentre("\t\t >> " .. MENU[i].text .. " <<")
    else
      printCentre("\t\t" .. MENU[i].text)
    end
    POSITION = POSITION + 1
    printCentre("\t\tSelection: " .. POSITION .. "/" .. #MENU)
  end
end

-- [[Program Entry Point ]] --

printHeader()

printMenu()