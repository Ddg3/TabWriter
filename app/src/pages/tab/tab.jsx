import React from "react";
import ROUTES from "Constants/routes";
import { Link } from "react-router-dom";
import "./tab.css";
import Mousetrap from 'mousetrap';

class Tab extends React.Component {
    constructor(props) {
      super(props);
      this.state = { 
        strings: ["1","2","3","4","5","6"],
        columns: ["inputs-0"],
        position: 0,
      };

      this.focusText = null;
      this.lastColumn = null;
      this.setFocusRef = element =>{
        this.focusText = element;
      }

      this.setLastColumnRef = element =>{
        this.lastColumn = element;
      }

      this.handleKeys = this.handleKeys.bind(this)
      this.copyLastColumn = this.copyLastColumn.bind(this)

      var Mousetrap = require('mousetrap');
      Mousetrap.prototype.stopCallback = function () {
        return false;
      }
      Mousetrap.bind('up', (e) => this.handleKeys(e, -1))
      Mousetrap.bind('down', (e) => this.handleKeys(e, 1))
      Mousetrap.bind('left', (e) => this.handleKeys(e, -6))
      Mousetrap.bind('right', (e) => this.handleKeys(e, 6))
      Mousetrap.bind(['ctrl+d', 'command+d'], (e) => this.copyLastColumn(e))
    }

    copyLastColumn(e){
      e.preventDefault()
      this.handleKeys(e, 6)
      this.appendInput()
    }
    handleKeys(e, increment){
      e.preventDefault()
      if(this.state.position + increment >= 0)
        this.setState(prevState => ({ position: prevState.position + increment}))
    }

    componentDidUpdate(){
      let position = this.state.position;
      let columns = this.state.columns.length;

      if(position / 6 > columns - 1)
        this.appendInput()

      if(this.focusText)
        this.focusText.focus(); 
      
    }

    appendInput() {
      var newInput = `input-${this.state.columns.length}`;
      this.setState(prevState => ({ columns: prevState.columns.concat([newInput]) }));
    }
    
    render() {
    return (
      <React.Fragment>
        <section className="section">
          <div className="input-container">
          {
            this.state.columns.map((col, i) =>
              <div className="input-column">
                {
                  this.state.strings.map((field, j) =>
                    <div className="input-field">
                      <input type="text" ref={(i*6)+j == this.state.position ? this.setFocusRef : null}></input>
                    </div>
                  )
                }
              </div>
            )
          }
          </div>
        </section>
      </React.Fragment>
    );
  }
}

export default Tab