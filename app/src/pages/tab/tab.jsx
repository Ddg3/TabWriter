import React from "react";
import ROUTES from "Constants/routes";
import { Link } from "react-router-dom";

class Tab extends React.Component {
    constructor(props) {
        super(props);
    
        this.state = {
          message: "",
        };
        this.onChangeMessage = this.onChangeMessage.bind(this);
        this.onSubmitMessage = this.onSubmitMessage.bind(this);
    }
    
    onChangeMessage(event) {
        const { value } = event.target;
        this.setState((state) => ({
          message: value,
        }));
      }
    
      onSubmitMessage(event) {
        event.preventDefault(); // prevent navigation
        this.props.changeMessage(this.state.message); // update redux store

        // reset
        this.setState((state) => ({
          message: "",
        }));
    }

    render() {
    return (
      <React.Fragment>
        <section className="section">
          <div className="container">
            <section className="hero is-info">
              <div className="hero-body">
                <p className="title">
                  AAAAAAAAAAAA!
                </p>
                <p className="subtitle">
                  Please navigate to view the features of this template.
                </p>
              </div>
            </section>
          </div>
        </section>
        <section className="section">
          <div className="container">
            <form className="mb-4" onSubmit={this.onSubmitMessage}>
              <div className="field is-horizontal">
                <input
                  placeholder="New message of the day"
                  className="input"
                  value={this.state.message}
                  onChange={this.onChangeMessage}></input>
                <input
                  className="button is-primary"
                  type="submit"
                  value="Save"></input>
              </div>
            </form>
          </div>
        </section>
      </React.Fragment>
    );
  }
}

// const mapStateToProps = (state, props) => ({
//     home: state.home,
//   });
// const mapDispatch = { changeMessage };

export default Tab