import React from "react";
import ROUTES from "Constants/routes";
import { Link } from "react-router-dom";
import "./tab.css";

class Tab extends React.Component {
    constructor(props) {
      super(props);
      this.state = { inputs: ["inputs-0", "inputs-1", "inputs-2", "inputs-3", "inputs-4", "inputs-5"] };
    }

    render() {
    return (
      <React.Fragment>
        <section className="section">
          <div className="input-container">
          {
            this.state.inputs.map(() =>
                <div className="input-field">
                  <input type="number"></input>
                </div>
            )
          }
          </div>
          <button onClick={ () => this.appendInput() }>
            ADD COLUMN
          </button>
        </section>
      </React.Fragment>
    );
  }

  appendInput() {
    for(let i = 0; i < 6; i++){
      var newInput = `input-${this.state.inputs.length}`;
      this.setState(prevState => ({ inputs: prevState.inputs.concat([newInput]) }));
    }
  }
}
// const mapStateToProps = (state, props) => ({
//     home: state.home,
//   });
// const mapDispatch = { changeMessage };

export default Tab