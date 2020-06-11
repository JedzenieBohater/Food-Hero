import React, { useState } from 'react'
import { connect } from 'react-redux'

export const AddOffer = ({
  sessionReducer: { userID },
  setView,
  updateDishList,
}) => {
  const [image, setImage] = useState(null)
  const [imageUrl, setImageUrl] = useState('')
  const [name, setName] = useState('')
  const [category, setCategory] = useState('')
  const [description, setDescription] = useState('')

  const handleImageChange = e => {
    const reader = new FileReader()
    const file = e.target.files[0]

    reader.onloadend = () => {
      setImage(file)
      setImageUrl(reader.result)
    }

    try {
      if (!file) {
        throw new Error('')
      }
      reader.readAsDataURL(file)
    } catch (err) {
      setImage(null)
      setImageUrl('')
    }
  }

  const handleSubmit = async e => {
    e.preventDefault()

    try {
      let response = await fetch(`/api/dish/${userID}`, {
        method: 'POST',
        body: JSON.stringify({ name, category, description }),
        headers: {
          'Content-Type': 'application/json',
        },
      })

      if (!response.ok) {
        throw new Error('Posting a dish not ok')
      }

      const { dishID } = await response.json()

      if (!!image) {
        let formData = new FormData()
        formData.append('imageFile', image)
        response = await fetch(`/api/dish/${dishID}/upload`, {
          method: 'POST',
          body: formData,
        })
        if (!response.ok) {
          throw new Error()
        }
      }

      setView('dishlist')
      updateDishList()
    } catch (err) {
      console.log(err)
    }
  }

  return (
    <div>
      <div className="content-box flexcolumn search searchmain">
        <div className="flexrow">
          <form className="flexrow fullwidth" onSubmit={handleSubmit}>
            <div className="col25 center">
              <img
                className="addpic"
                id="blah"
                src={
                  !!imageUrl
                    ? imageUrl
                    : `${process.env.PUBLIC_URL}/static/images/logo.svg`
                }
                alt="insert image"
              />
              <div>
                <input
                  type="file"
                  accept="image/png, image/jpeg"
                  id="imgInp"
                  onChange={handleImageChange}
                />
              </div>
            </div>
            <div className="col75">
              <div className="title">
                <input className="addtitle" type="text" placeholder="title" />
              </div>
              <div>
                <input
                  value={name}
                  className="marginer width33"
                  type="text"
                  placeholder="name"
                  onChange={e => setName(e.target.value)}
                />
                <input
                  value={category}
                  className="marginer width33"
                  type="text"
                  placeholder="category"
                  onChange={e => setCategory(e.target.value)}
                />
              </div>
              <div className="marginer">
                <textarea
                  value={description}
                  className="adddesc"
                  placeholder="description"
                  onChange={e => setDescription(e.target.value)}
                />
              </div>
              <div className="center">
                <button type="submit" className="btn-blue">
                  add
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  )
}

const mapStateToProps = ({ sessionReducer }) => ({
  sessionReducer,
})

export default connect(mapStateToProps)(AddOffer)
