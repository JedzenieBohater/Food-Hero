import React from 'react'
import { connect } from 'react-redux'

class Table extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      currentPage: 1
    }
  }

  setPage = (num) => {
    this.setState({
      currentPage: num
    })
  }

  render() {
    const len = this.props.data ? this.props.data.length : 1
    const itemsPerPage = 5
    const difference = len % itemsPerPage === 0 ? 0 : (itemsPerPage - len % itemsPerPage)
    const pageCount = (len + difference) / itemsPerPage
    var itemsToDisplay = this.props.data ? this.props.data.slice((this.state.currentPage - 1) * itemsPerPage, (this.state.currentPage - 1) * itemsPerPage + itemsPerPage) : []

    // TODO css
    return (
      <div>
        {itemsToDisplay}
        <button onClick={() => this.setPage(this.state.currentPage - 1)} disabled={this.state.currentPage === 1 ? true : false}>&lt;</button>
        <label>{this.props.lang.page}: {this.state.currentPage}</label>
        <button onClick={() => this.setPage(this.state.currentPage + 1)} disabled={this.state.currentPage === pageCount ? true : false}>&gt;</button>
      </div>
    )
  }
}

const mapStateToProps = ({ languageReducer }) => ({
  lang: languageReducer.table,
})

export default connect(mapStateToProps)(Table)