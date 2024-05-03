import express, { Request, Response } from "express";
import verifyToken from "../middleware/auth";
import Hotel from "../models/hotel";
import { HotelSearchResponse } from "../shared/types";
import { param, validationResult } from "express-validator";
const router = express.Router();

router.get("/search", async (req: Request, res: Response) => {
    try {
        const pageSize = 5;
        const pageNumber = parseInt(
            req.query.page ? req.query.page.toString() : "1"
        );
        //pages to skip
        const skip = (pageNumber - 1) * pageSize;

        const hotels = await Hotel.find().skip(skip).limit(pageSize);
        const total = await Hotel.countDocuments();
        const response: HotelSearchResponse = {
            data: hotels,
            pagination: {
                total,
                page: pageNumber,
                pages: Math.ceil(total / pageSize),
            },
        };

        router.get(
            "/:id",
            [param("id").notEmpty().withMessage("Hotel ID is required")],
            async (req: Request, res: Response) => {
                const errors = validationResult(req);
                if (!errors.isEmpty()) {
                    return res.status(400).json({ errors: errors.array() });
                }
                const id = req.params.id.toString();

                try {
                    const hotel = await Hotel.findById(id);
                    res.json(hotel);
                } catch (error) {
                    console.log(error);
                    res.status(500).json({ message: "Error fetching hotel" });
                }
            }
        );

        res.json(response);
    } catch (error) {
        console.log("error", error);
        res.status(500).json({ message: "Something went wrong" });
    }
});

export default router;
